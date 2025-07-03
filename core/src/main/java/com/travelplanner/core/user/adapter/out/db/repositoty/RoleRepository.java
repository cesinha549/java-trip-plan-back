package com.travelplanner.core.user.adapter.out.db.repositoty;

import com.travelplanner.core.user.adapter.out.db.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByName(String name);
}
