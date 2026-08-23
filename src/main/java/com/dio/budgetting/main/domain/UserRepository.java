package com.dio.budgetting.main.domain;

public interface UserRepository {
	User save(User user);
	User findByUsername(String username);
}
