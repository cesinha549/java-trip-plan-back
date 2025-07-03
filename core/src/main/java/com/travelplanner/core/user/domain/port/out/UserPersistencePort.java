package com.travelplanner.core.user.domain.port.out;


import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;

public interface UserPersistencePort {

    public UserRegisterResponseModel saveUser(UserModel userModel);

    public UserModel findUser(String userName);
}
