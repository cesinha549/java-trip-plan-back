package com.travelplanner.core.user.adapter.in.web.dto.login;

import com.travelplanner.core.user.domain.model.login.UserLoginRequestModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;

public record UserLoginRequestDTO(String email, String password) {

    public UserLoginRequestModel toDomain() {
        return new UserLoginRequestModel(email(), password());
    }
}
