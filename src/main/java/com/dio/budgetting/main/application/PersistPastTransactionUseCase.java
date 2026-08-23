package com.dio.budgetting.main.application;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import com.dio.budgetting.main.application.input.PersistPastTransactionInput;
import com.dio.budgetting.main.application.output.TransactionOutput;
import com.dio.budgetting.main.domain.Transaction;
import com.dio.budgetting.main.domain.TransactionRepository;
import com.dio.budgetting.main.domain.UserRepository;

@Service
public class PersistPastTransactionUseCase {
	
	private TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	
	public PersistPastTransactionUseCase(TransactionRepository transactionRepository, UserRepository userRepository) {
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
	}
	
	@Tool(name = "persist-past-transaction", description = "Persiste uma transação financeira passada/antiga informando a data e hora em que ocorreu")
	public TransactionOutput execute(PersistPastTransactionInput input) {
		
		var userLogged = userRepository.findByUsername(input.getUsername());
		
		var transaction = transactionRepository.save(
				new Transaction(userLogged, input.getDescription(), input.getAmount(), input.getCategory(), input.getCreatedAt()));
		return TransactionOutput.from(transaction);
	}

}
