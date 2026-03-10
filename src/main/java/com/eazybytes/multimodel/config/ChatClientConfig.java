package com.eazybytes.multimodel.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*creo e configuro i bean ChatClient per per i model openai e ollama...*/
@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.create(openAiChatModel);
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        /*Il metodo create() usato sopra non fa altro che creare un ChatClient.Builder
        * e invocare su di esso il metodo build() come segue...*/
        ChatClient.Builder chatClientBuilder = ChatClient.builder(ollamaChatModel);
        return chatClientBuilder.build();
        /*Con questo tipo di configurazione si ha maggiore controllo su quale deve essere il
        * comportamento del bean ChatClient in vocando altri metodi sul ChatClientBuilder prima del build()...*/
    }

}
