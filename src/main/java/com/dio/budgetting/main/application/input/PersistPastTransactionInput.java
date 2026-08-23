package com.dio.budgetting.main.application.input;

import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.ToolParam;

import com.dio.budgetting.main.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersistPastTransactionInput {
	
	@ToolParam(description = "Descrição do gasto")
	private String description;
	
	@ToolParam(description = "Valor do gasto (em centavos)")
	private long amount;
	
	@ToolParam(description = "Categoria de uma transação")
	private Category category;
	
	@ToolParam(description = "Data da transação")
	private LocalDateTime createdAt;
	
	@ToolParam(description = "Usuário da transação")
	private String username;

}
