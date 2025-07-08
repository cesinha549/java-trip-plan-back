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

// UserCommandService.java

    @Override
    public UserRegisterResponseModel register(UserRegisterRequestModel userRegisterRequest) {

        // 1. Hash the password first
        String hashedPassword = passwordEncoder.encode(userRegisterRequest.password());

        // 2. Create the complete user model with the hashed password
        UserModel userToSave = new UserModel(
                null,
                userRegisterRequest.name(),
                userRegisterRequest.email(),
                hashedPassword,
                null // Roles will be assigned by the persistence adapter
        );

        // 3. Save the user to the database FIRST
        UserRegisterResponseModel response = userPersistencePort.saveUser(userToSave);

        // 4. Create a safe, non-sensitive event model
        //    (You might want a dedicated model for events, e.g., UserRegisteredEvent)
        UserModel userEventModel = new UserModel(
                response.userId(), // Use the ID from the saved user
                userRegisterRequest.name(),
                userRegisterRequest.email(),
                null, // NEVER include the password
                null
        );

        // 5. Send the event AFTER the user is successfully saved
        userRegisterEventProducer.sendRegisterEvent(userEventModel);

        // 6. Return the response to the controller
        return response;
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
