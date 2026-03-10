
package com.eazybytes.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*Vogliamo che l'utente invii un messaggio come se fosse un prompt  ad OpenAI  LLM Model
 * e che qualunque risposta fornita dal LLM model sia restituita all'utente...
 * In questo caso ho creato due differenti metodi rest per interrogare due differenti LLM , openai e ollama
 * openai viene interrogato daremoto ollama gira in una sua versione ridota in locale...*/
@RestController
@RequestMapping("/api")
public class ChatController {

    //Innietto i bean configurati nella classe ChatClientConfig
    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;

    @Autowired //con l'annotazione @Qualifier inietto i bean ChatCLient tramite il nome del loro metodo di configurazione...
    public ChatController(@Qualifier("openAiChatClient") ChatClient openAiChatClient,
                                        @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {

        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;

    }


    @GetMapping("/openai/chat")
    public ResponseEntity<String> openaAIChat(@RequestParam("message") String message) {

        /*con il bean openAiChatClient possiamo comunicare con llm OpenAI*/
        String llmResponse = openAiChatClient
                .prompt(message) //passo il messaggio
                .call() //inizializza la chat con il model LLM
                .content(); //restituisce la risposta del LLM Model

        return ResponseEntity.ok().body(llmResponse);
    }


    @GetMapping("/ollama/chat")
    public ResponseEntity<String> ollamaChat(@RequestParam("message") String message) {

        /*come sopra ma con model LLM Ollama*/
        String llmResponse = ollamaChatClient
                .prompt(message)
                .call()
                .content();

        return ResponseEntity.ok().body(llmResponse);
    }

}
/*AFFINCHE' QUESTO CODICE FUNZIONI E' NECCESSARIO OTTENERE UNA
 * CHIAVE PRIVATA DA OPENAI PER LA QUALE E' OBBLIGATIORIO AVERE UN CREDITO
 * IN DENARO VEDERE SUL SITO CHATGPT PER OTTENERLA
 * Una volta ottenuta la secret key va configurata nell'application.yml
*/