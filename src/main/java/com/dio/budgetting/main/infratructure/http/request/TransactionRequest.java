package com.dio.budgetting.main.infratructure.http.request;

import com.dio.budgetting.main.application.input.PersistTransactionInput;
import com.dio.budgetting.main.domain.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest(
		@NotBlank(message = "A descrição não pode estar em branco")
		String description, 
		
		@NotNull(message = "A categoria é obrigatória")
		Category category, 
		
		@Positive(message = "O valor (amount) deve ser maior que zero")
		long amount) {
    public PersistTransactionInput toInput(String username) {
        return new PersistTransactionInput(description, amount, category, username);
    }
}