package com.travelplanner.core.user.application;

import com.travelplanner.core.user.adapter.out.db.RoleEntity;
import com.travelplanner.core.user.adapter.out.db.UserMapper;
import com.travelplanner.core.user.domain.model.RoleModel;
import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.model.login.UserLoginRequestModel;
import com.travelplanner.core.user.domain.model.login.UserLoginResponseModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;
import com.travelplanner.core.user.domain.port.in.UserUseCase;
import com.travelplanner.core.user.domain.port.out.UserPersistencePort;
import com.travelplanner.security.JwtUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserUseCase {

    private final UserPersistencePort userPersistencePort;

    private final JwtUtil jwtUtil;

    public UserService(UserPersistencePort userPersistencePort, JwtUtil jwtUtil) {
        this.userPersistencePort = userPersistencePort;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserRegisterResponseModel register(UserRegisterRequestModel userRegisterRequest) {

        // Map basic fields from request to domain user model
        UserModel userModel = UserMapper.fromRegister(userRegisterRequest);

        // Create full domain user with role assigned
        UserModel userWithRoles = new UserModel(
                null, // id will be generated later
                userModel.name(),
                userModel.email(),
                userModel.password(),
            null
        );

        return userPersistencePort.saveUser(userWithRoles);
    }

    @Override
    public UserLoginResponseModel login(UserLoginRequestModel userLoginRequest) {
       UserModel user =  userPersistencePort.findUser(userLoginRequest.user());

        if (!userLoginRequest.password().equals(user.password())) {
            throw new RuntimeException("Invalid email or password");
        }

        // 3. Wrap into UserDetails
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.email(),
                user.password(),
                user.roles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .toList()
        );

        // 4. Generate token
        String token = jwtUtil.generateToken(userDetails);

        // 4. Return login response
        return new UserLoginResponseModel(token, "Bearer", user.id());
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return Optional.ofNullable(userPersistencePort.findUser(email));
    }
}
