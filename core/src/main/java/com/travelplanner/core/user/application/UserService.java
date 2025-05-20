package com.travelplanner.core.user.application;

import com.travelplanner.core.user.domain.model.login.UserLoginRequestModel;
import com.travelplanner.core.user.domain.model.login.UserLoginResponseModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;
import com.travelplanner.core.user.domain.port.in.UserUseCase;

public class UserService implements UserUseCase {

    @Override
    public UserRegisterResponseModel register(UserRegisterRequestModel userRegisterRequest) {
        return null;
    }

    @Override
    public UserLoginResponseModel login(UserLoginRequestModel userLoginRequest) {
        return null;
    }
}
