package com.travelplanner.core.user.adapter.out.db;

import com.travelplanner.core.user.domain.model.RoleModel;
import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.model.register.UserRegisterRequestModel;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity toEntity(UserModel model, Set<RoleEntity> roles) {
        UserEntity entity = new UserEntity();
        entity.setId(model.id());
        entity.setName(model.name());
        entity.setEmail(model.email());
        entity.setPassword(model.password());
        entity.setRoles(roles); // 💥 here you inject real RoleEntities
        return entity;
    }
    public static UserModel fromRegister(UserRegisterRequestModel userRegister) {

        return new UserModel(null, userRegister.name(), userRegister.email(), userRegister.password(),null);
    }

    public static Set<RoleEntity> toRoleEntity(Set<RoleModel> roleModel) {
        Set<RoleEntity> roleEntitySet = new java.util.HashSet<>(Set.of());

    roleModel.forEach(role ->{
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(Long.valueOf(role.id()));
        roleEntity.setName(role.name());
        roleEntitySet.add(roleEntity);
    });
    return  roleEntitySet;
    }

    public static UserModel fromEntity(UserEntity entity) {
        Set<RoleModel> roles = entity.getRoles().stream()
                .map(roleEntity -> new RoleModel(
                        roleEntity.getId() != null ? roleEntity.getId().toString() : null,
                        roleEntity.getName()
                ))
                .collect(Collectors.toSet());

        return new UserModel(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                roles
        );
    }



}
