package com.travelplanner.core.user.adapter.out.db;

import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;
import com.travelplanner.core.user.domain.port.out.UserPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRegisterResponseModel saveUser(UserModel userModel) {

     var  userRegister = userRepository.save(UserMapper.toEntity(userModel));

     return new UserRegisterResponseModel(userRegister.getId());
    }

    @Override
    public String findUser(String userName) {
        return "";
    }
}
