package com.dio.budgetting.main.application;

import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import com.dio.budgetting.main.application.input.PersistTransactionInput;
import com.dio.budgetting.main.application.output.TransactionOutput;
import com.dio.budgetting.main.domain.Transaction;
import com.dio.budgetting.main.domain.TransactionRepository;
import com.dio.budgetting.main.domain.UserRepository;

@Service
public class PersistTransactionUseCase {
	
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Tool(name = "persist-transaction", description = "Persiste uma nova transação financeira")
    public TransactionOutput execute(PersistTransactionInput input) {
    	
    	var userLogged = userRepository.findByUsername(input.username());
    	
        var transaction = transactionRepository.save(
                new Transaction(userLogged, input.description(), input.amount(), input.category(), LocalDateTime.now()));

        return TransactionOutput.from(transaction);
    }

}