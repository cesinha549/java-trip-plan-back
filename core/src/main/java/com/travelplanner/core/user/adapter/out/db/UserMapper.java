package com.travelplanner.core.user.adapter.out.db;

import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;

public class UserMapper {

    public static UserEntity toEntity(UserModel model) {
        UserEntity entity = new UserEntity();
        entity.setId(model.id());
        entity.setName(model.name());
        entity.setEmail(model.email());
        entity.setPassword(model.password());
        return entity;
    }

    public static UserModel fromRegister(UserRegisterRequestModel userRegister) {

        return new UserModel(null, userRegister.name(), userRegister.email(), userRegister.password(),null);
    }



}
