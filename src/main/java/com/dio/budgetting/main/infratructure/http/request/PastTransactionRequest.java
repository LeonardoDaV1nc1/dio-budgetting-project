package com.dio.budgetting.main.infratructure.http.request;

import java.time.LocalDateTime;

import com.dio.budgetting.main.application.input.PersistPastTransactionInput;
import com.dio.budgetting.main.domain.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class PastTransactionRequest{
	
	@NotBlank(message = "A descrição não pode estar em branco")
	private	String description; 
		
	@NotNull(message = "A categoria é obrigatória")
	private	Category category;
	
	@Positive(message = "O valor (amount) deve ser maior que zero")
	private	long amount;
	
	@NotNull(message = "A data de criação é obrigatória")
	@Past(message = "A data não pode ser no futuro")
	private	LocalDateTime createdAt;
		
    public PersistPastTransactionInput toInput(String username) {
        return new PersistPastTransactionInput(description, amount, category, createdAt, username);
    }
    
}