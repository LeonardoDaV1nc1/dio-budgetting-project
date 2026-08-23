package com.dio.budgetting.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAIChatModelIT {
	
	@Autowired
    OpenAiChatModel openAiChatModel;

    @Test
    void should_receiveResponse_when_chatModelIsCalled() {
        var chatClient = ChatClient.builder(openAiChatModel)
                .defaultSystem("Você é um assistente financeiro")
                .build();

        var response = chatClient.prompt("Gere um registro de budgeting, com descrição de gasto, valor em reais e local")
                .call()
                .content();

        assertThat(response).isNotEmpty();
        System.out.println(response);
    }
}
