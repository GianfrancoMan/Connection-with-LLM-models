
package com.eazybytes.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;

    @Autowired
    public ChatController(@Qualifier("openAiChatClient") ChatClient openAiChatClient,
                                        @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {

        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;

    }


    @GetMapping("/openai/chat")
    public ResponseEntity<String> openaAIChat(@RequestParam("message") String message) {

        
        String llmResponse = openAiChatClient
                .prompt(message)
                .call()
                .content();
        return ResponseEntity.ok().body(llmResponse);
    }


    @GetMapping("/ollama/chat")
    public ResponseEntity<String> ollamaChat(@RequestParam("message") String message) {

        String llmResponse = ollamaChatClient
                .prompt(message)
                .call()
                .content();

        return ResponseEntity.ok().body(llmResponse);
    }
}
