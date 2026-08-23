package com.dio.budgetting.main.infratructure.persistence.repository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.dio.budgetting.main.domain.User;
import com.dio.budgetting.main.domain.UserRepository;
import com.dio.budgetting.main.infratructure.persistence.entity.UserEntity;

@Repository
public class JpaUserRepository implements UserRepository {
    
	private final UserEntityRepository userEntityRepository;

    public JpaUserRepository(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public User save(User user) {
        var entity = UserEntity.from(user);
        return userEntityRepository.save(entity).toDomain();
    }

	@Override
	public User findByUsername(String username) {
		return userEntityRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username))
	            .toDomain();
	}

}