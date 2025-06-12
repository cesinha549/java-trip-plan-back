package com.travelplanner.core.user.domain.port.in;

import com.travelplanner.core.user.domain.model.UserModel;

import java.util.Optional;

public interface UserQueryUseCase {


    Optional<UserModel> findByEmail(String email);
}
