package com.dio.budgetting.main.infratructure.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.List;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.dio.budgetting.main.application.ListTransactionsByCategoryUseCase;
import com.dio.budgetting.main.application.PersistPastTransactionUseCase;
import com.dio.budgetting.main.application.PersistTransactionUseCase;
import com.dio.budgetting.main.application.RemoveLastTransactionUseCase;
import com.dio.budgetting.main.domain.Category;
import com.dio.budgetting.main.infratructure.http.request.PastTransactionRequest;
import com.dio.budgetting.main.infratructure.http.request.TransactionRequest;
import com.dio.budgetting.main.infratructure.http.response.TransactionResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
    private final PersistTransactionUseCase persistTransactionUseCase;
    private final PersistPastTransactionUseCase persistPastTransactionUseCase;
    private final ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;
    private final RemoveLastTransactionUseCase removeLastTransactionUseCase; 

    private final TranscriptionModel transcriptionModel;
    private final ChatClient chatClient;
    private final TextToSpeechModel textToSpeechModel;

    public TransactionController(PersistTransactionUseCase persistTransactionUseCase,
    							 PersistPastTransactionUseCase persistPastTransactionUseCase,
                                 ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase,
                                 RemoveLastTransactionUseCase removeLastTransactionUseCase,
                                 TranscriptionModel transcriptionModel,
                                 @Value("classpath:prompts/system-message.st") Resource systemPrompt,
                                 ChatClient.Builder chatClientBuilder,
                                 TextToSpeechModel textToSpeechModel) throws IOException {
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.persistPastTransactionUseCase = persistPastTransactionUseCase;
        this.listTransactionsByCategoryUseCase = listTransactionsByCategoryUseCase;
        this.removeLastTransactionUseCase = removeLastTransactionUseCase;
        this.transcriptionModel = transcriptionModel;
        this.chatClient = chatClientBuilder
                .defaultSystem(systemPrompt.getContentAsString(Charset.defaultCharset()))
                .defaultTools(persistTransactionUseCase, removeLastTransactionUseCase, listTransactionsByCategoryUseCase)
                .build();
        this.textToSpeechModel = textToSpeechModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody @Valid TransactionRequest request, Principal principal) {
    	String username = principal.getName();
        var transaction = persistTransactionUseCase.execute(request.toInput(username));
        return TransactionResponse.from(transaction);
    }
    
    @PostMapping("/past")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createPastTransaction(@RequestBody @Valid PastTransactionRequest request, Principal principal) {
    	String username = principal.getName();
        var transaction = persistPastTransactionUseCase.execute(request.toInput(username));
        return TransactionResponse.from(transaction);
    }
    
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeLastTransaction(Principal principal) {
    	String username = principal.getName();
        removeLastTransactionUseCase.execute(username);
    }

    @GetMapping("/{category}")
    public List<TransactionResponse> readTransactions(@PathVariable("category") String category, Principal principal) {
    	String username = principal.getName();
    	
    	Category vCategory;
        try {
        	vCategory = Category.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida: " + category);
        }
        
        return listTransactionsByCategoryUseCase.execute(vCategory, username).stream().map(TransactionResponse::from).toList();
    }

    @PostMapping(value = "/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mp3")
    ResponseEntity<Resource> transcribe(@RequestParam("file") MultipartFile file, Principal principal) {
    	String username = principal.getName();
    	
        var userMessage = transcriptionModel.transcribe(file.getResource());
        
        userMessage = userMessage + " para o usuario: " + username;
        var result = chatClient
        				.prompt()
        				.user(userMessage)
        				.call().content();

        byte[] audio = textToSpeechModel.call(result);
        var resource = new ByteArrayResource(audio);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.mp3")
                                .build()
                                .toString())
                .body(resource);
    }
}
