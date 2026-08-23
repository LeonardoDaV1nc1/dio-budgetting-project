package com.dio.budgetting.main.infratructure.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dio.budgetting.main.domain.Category;
import com.dio.budgetting.main.domain.Transaction;
import com.dio.budgetting.main.domain.TransactionRepository;
import com.dio.budgetting.main.infratructure.persistence.entity.TransactionEntity;

@Repository
public class JpaTransactionRepository implements TransactionRepository {
    
	private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return transactionEntityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return transactionEntityRepository.findAllByCategory(category)
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }
    
    @Override
	public List<Transaction> findAllByCategoryAndUserUsername(Category category, String username) {
    	return transactionEntityRepository.findAllByCategoryAndUserUsername(category, username)
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
	}

	@Override
	public void deleteLastTransaction(String username) {
		transactionEntityRepository.findTopByUserUsernameOrderByCreatedAtDesc(username)
						.ifPresent(transactionEntityRepository::delete);
	}

	

}