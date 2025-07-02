package com.travelplanner.core.user.application;

import com.travelplanner.core.user.adapter.out.db.UserMapper;
import com.travelplanner.core.user.adapter.out.kafka.UserRegisterEventProducer;
import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.model.login.UserLoginRequestModel;
import com.travelplanner.core.user.domain.model.login.UserLoginResponseModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;
import com.travelplanner.core.user.domain.port.in.UserCommandUseCase;
import com.travelplanner.core.user.domain.port.out.UserEventPort;
import com.travelplanner.core.user.domain.port.out.UserPersistencePort;
import com.travelplanner.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandService implements UserCommandUseCase {

    private final UserPersistencePort userPersistencePort;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserEventPort userRegisterEventProducer;

    public UserCommandService(UserPersistencePort userPersistencePort, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserRegisterEventProducer userRegisterEventProducer) {
        this.userPersistencePort = userPersistencePort;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRegisterEventProducer = userRegisterEventProducer;
    }

    @Override
    public UserRegisterResponseModel register(UserRegisterRequestModel userRegisterRequest) {

        // Map basic fields from request to domain user model
        UserModel userModel = UserMapper.fromRegister(userRegisterRequest);

        // On registration
        String hashedPassword = passwordEncoder.encode(userRegisterRequest.password());

        // Create full domain user with role assigned
        UserModel userWithRoles = new UserModel(
                null, // id will be generated later
                userModel.name(),
                userModel.email(),
                hashedPassword,
            null
        );

        userRegisterEventProducer.sendRegisterEvent();
        return userPersistencePort.saveUser(userWithRoles);
    }

    @Override
    public UserLoginResponseModel login(UserLoginRequestModel userLoginRequest) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.user(), userLoginRequest.password()));

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        // 4. Generate token
        String token = jwtUtil.generateToken(userDetails);

        // 4. Return login response
        return new UserLoginResponseModel(token, "Bearer");
    }

}
