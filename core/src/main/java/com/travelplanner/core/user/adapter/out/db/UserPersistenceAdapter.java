package com.travelplanner.core.user.adapter.out.db;

import com.travelplanner.core.user.adapter.out.db.entity.RoleEntity;
import com.travelplanner.core.user.adapter.out.db.entity.UserEntity;
import com.travelplanner.core.user.adapter.out.db.repositoty.RoleRepository;
import com.travelplanner.core.user.adapter.out.db.repositoty.UserRepository;
import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterResponseModel;
import com.travelplanner.core.user.domain.port.out.UserPersistencePort;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    private  final RoleRepository roleRepository;

    public UserPersistenceAdapter(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRegisterResponseModel saveUser(UserModel userModel) {


        RoleEntity travellerRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Set<RoleEntity> roles = Set.of(travellerRole);

        UserEntity entity = UserMapper.toEntity(userModel, roles);
        UserEntity saved = userRepository.save(entity);

        return new UserRegisterResponseModel(saved.getId());
    }

    @Override
    public UserModel findUser(String userName) {
       UserEntity  userEntity = userRepository.findByEmail(userName).orElseThrow(() -> new RuntimeException("User Not Found"));

      return  UserMapper.fromEntity(userEntity);

    }
}
