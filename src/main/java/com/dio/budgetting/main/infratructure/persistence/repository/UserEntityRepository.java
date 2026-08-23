package com.dio.budgetting.main.infratructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dio.budgetting.main.infratructure.persistence.entity.UserEntity;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}
