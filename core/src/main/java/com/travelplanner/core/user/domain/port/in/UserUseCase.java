package com.travelplanner.core.user.domain.port.in;

import com.travelplanner.core.user.domain.model.login.UserLoginRequestModel;
import com.travelplanner.core.user.domain.model.login.UserLoginResponseModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;
import com.travelplanner.core.user.domain.model.UserModel;

import java.util.Optional;


public interface UserUseCase {

    UserRegisterResponseModel register(UserRegisterRequestModel userRegisterRequest);

    UserLoginResponseModel login(UserLoginRequestModel userLoginRequest);

    Optional<UserModel> findByEmail(String email);
}
