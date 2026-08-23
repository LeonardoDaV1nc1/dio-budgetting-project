package com.dio.budgetting.main.application.input;

import org.springframework.ai.tool.annotation.ToolParam;

import com.dio.budgetting.main.domain.Category;

public record PersistTransactionInput(
		@ToolParam(description = "Descrição do gasto") String description,
        @ToolParam(description = "Valor do gasto (em centavos)") long amount,
        @ToolParam(description = "Categoria de uma transação") Category category,
        @ToolParam(description = "Usuário de uma transação") String username) {
}
