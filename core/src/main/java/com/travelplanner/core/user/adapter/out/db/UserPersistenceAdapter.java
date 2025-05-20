package com.travelplanner.core.user.adapter.out.db;

import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;
import com.travelplanner.core.user.domain.port.out.UserPersistencePort;

public class UserPersistenceAdapter implements UserPersistencePort {

    @Override
    public String saveUser(UserRegisterRequestModel tripModel) {
        return "";
    }

    @Override
    public String findUser(String userName) {
        return "";
    }
}
