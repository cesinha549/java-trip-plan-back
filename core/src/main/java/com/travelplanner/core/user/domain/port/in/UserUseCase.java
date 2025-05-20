package com.travelplanner.core.user.domain.port.in;

import com.travelplanner.core.user.domain.model.login.UserLoginRequestModel;
import com.travelplanner.core.user.domain.model.login.UserLoginResponseModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;

public interface UserUseCase {

    UserRegisterResponseModel register(UserRegisterRequestModel userRegisterRequest);

    UserLoginResponseModel login(UserLoginRequestModel userLoginRequest);
}
