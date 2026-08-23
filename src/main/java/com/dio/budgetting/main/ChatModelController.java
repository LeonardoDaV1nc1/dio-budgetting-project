package com.dio.budgetting.main;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatModelController {
	
    private final ChatClient chatClient;
    
    public ChatModelController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}
    
    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
    	return this.chatClient.prompt().user(prompt).call().content();
    }

}
