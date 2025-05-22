package com.travelplanner.core.user.application;

import com.travelplanner.core.user.adapter.out.db.RoleEntity;
import com.travelplanner.core.user.adapter.out.db.UserMapper;
import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.model.login.UserLoginRequestModel;
import com.travelplanner.core.user.domain.model.login.UserLoginResponseModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;
import com.travelplanner.core.user.domain.port.in.UserUseCase;
import com.travelplanner.core.user.domain.port.out.UserPersistencePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    private final UserPersistencePort userPersistencePort;

    public UserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserRegisterResponseModel register(UserRegisterRequestModel userRegisterRequest) {

        return userPersistencePort.saveUser(UserMapper.fromRegister(userRegisterRequest));
    }

    @Override
    public UserLoginResponseModel login(UserLoginRequestModel userLoginRequest) {
        return null;
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return null;
    }
}
