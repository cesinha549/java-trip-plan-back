package com.travelplanner.core.user.adapter.in.web.dto.login;

import com.travelplanner.core.user.domain.model.login.UserLoginResponseModel;

public record UserLoginResponseDTO(String token) {

    public static UserLoginResponseDTO fromDomain(UserLoginResponseModel model) {
        return new UserLoginResponseDTO(model.accessToken());
    }
}
