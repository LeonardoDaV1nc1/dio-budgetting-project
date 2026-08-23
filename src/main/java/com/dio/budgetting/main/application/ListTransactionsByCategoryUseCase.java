package com.dio.budgetting.main.application;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import com.dio.budgetting.main.application.output.TransactionOutput;
import com.dio.budgetting.main.domain.Category;
import com.dio.budgetting.main.domain.TransactionRepository;

@Service
public class ListTransactionsByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionsByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "list-transactions-by-category", description = "Lista transações financeiras por categoria")
    public List<TransactionOutput> execute(
    			@ToolParam(description = "Categoria de uma transação") Category category,
    			@ToolParam(description = "Username de uma transação") String username) {
        return transactionRepository.findAllByCategoryAndUserUsername(category, username).stream().map(TransactionOutput::from).toList();
    }
}