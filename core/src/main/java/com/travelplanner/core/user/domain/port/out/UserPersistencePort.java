package com.travelplanner.core.user.domain.port.out;


import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;

public interface UserPersistencePort {

    public String saveUser(UserRegisterRequestModel tripModel);

    public String findUser(String userName);
}
