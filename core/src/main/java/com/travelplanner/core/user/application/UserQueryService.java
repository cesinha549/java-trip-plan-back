package com.travelplanner.core.user.application;

import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.port.in.UserQueryUseCase;
import com.travelplanner.core.user.domain.port.out.UserPersistencePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryService implements UserQueryUseCase {

    private final UserPersistencePort userPersistencePort;

    public UserQueryService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return Optional.ofNullable(userPersistencePort.findUser(email));
    }
}
