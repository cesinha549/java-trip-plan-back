package com.travelplanner.core.user.domain.model;

import java.util.Set;

public record UserModel(String id, String name, String email, String password, Set<RoleModel> roles) {}
