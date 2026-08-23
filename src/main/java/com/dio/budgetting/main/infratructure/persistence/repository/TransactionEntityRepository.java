package com.dio.budgetting.main.infratructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.dio.budgetting.main.domain.Category;
import com.dio.budgetting.main.infratructure.persistence.entity.TransactionEntity;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByCategory(Category category);
    List<TransactionEntity> findAllByCategoryAndUserUsername(Category category, String username);
    Optional<TransactionEntity> findTopByUserUsernameOrderByCreatedAtDesc(String username);
}