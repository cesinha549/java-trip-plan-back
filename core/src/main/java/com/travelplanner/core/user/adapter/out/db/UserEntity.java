package com.travelplanner.core.user.adapter.out.db;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        @Column(unique = true)
        private String email;

        private String password;

        private String name;

        private boolean enabled;

        @ManyToMany(fetch = FetchType.EAGER)
        private Set<RoleEntity> roles;
}
