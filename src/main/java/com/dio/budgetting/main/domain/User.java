package com.dio.budgetting.main.domain;

import java.util.HashSet;
import java.util.Set;

import com.dio.budgetting.main.domain.id.UserId;
import com.dio.budgetting.main.infratructure.persistence.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
	
	private UserId id;
	private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
    
    public User(String username, String password) {
    	this.id = new UserId();
    	this.username = username;
    	this.password = password;
    	this.roles.add(Role.USERS);
    }

}