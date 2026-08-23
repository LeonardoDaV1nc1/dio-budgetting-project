package com.dio.budgetting.main.infratructure.persistence.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USERS;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
