package com.travelplanner.core.user.adapter.in.web.dto.register;

import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;

public record UserRegisterRequestDTO(String name, String email, String password) {


    public UserRegisterRequestModel toDomain() {
        return new UserRegisterRequestModel(name(), email(), password());
    }
}
