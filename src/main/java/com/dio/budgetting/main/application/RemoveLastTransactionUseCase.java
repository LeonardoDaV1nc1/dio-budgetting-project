package com.dio.budgetting.main.application;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import com.dio.budgetting.main.domain.TransactionRepository;
import com.dio.budgetting.main.domain.UserRepository;

@Service
public class RemoveLastTransactionUseCase {
	
	private final TransactionRepository transactionRepository;
	
	public RemoveLastTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "remover-last-transaction", description = "Remover a última transação financeira")
    public void execute(String username) {
        transactionRepository.deleteLastTransaction(username);
    }

}
