package com.dio.budgetting.main.domain;

import java.util.List;

public interface TransactionRepository {
	Transaction save(Transaction transaction);
	void deleteLastTransaction(String username);
    List<Transaction> findAllByCategory(Category category);
    List<Transaction> findAllByCategoryAndUserUsername(Category category, String username);
}
