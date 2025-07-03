package com.travelplanner.core.user.adapter.in.web.dto.register;

import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;

public record UserRegisterResponseDTO(String userId) {

    public static UserRegisterResponseDTO fromDomain(UserRegisterResponseModel userRegisterResponseModel) {

        return new UserRegisterResponseDTO(userRegisterResponseModel.userId());
    }
}
