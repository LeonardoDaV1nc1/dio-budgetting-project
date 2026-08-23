package com.dio.budgetting.main;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatClientController {
	
    private final OpenAiChatModel openAiChatModel;
    
    public ChatClientController(OpenAiChatModel openAiChatModel) {
		this.openAiChatModel = openAiChatModel;
	}
    
    @GetMapping("/chat-model")
    public String chat(@RequestParam("prompt") String prompt) {
    	return this.openAiChatModel.call(prompt);
    }

}
