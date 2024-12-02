package com.openai.chatgpt.controllers;

import com.openai.chatgpt.dtos.ChatBotRequest;
import com.openai.chatgpt.dtos.ChatBotResponse;
import com.openai.chatgpt.dtos.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ChatBotController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.chatgpt.model}")
    private String model;

    @Value("${openai.chatgpt.max-completions}")
    private int maxCompletions;

    @Value("${openai.chatgpt.temperature}")
    private double temperature;

    @Value("${openai.chatgpt.api.url}")
    private String apiUrl;

    @PostMapping("/chat")
    public ChatBotResponse chat(@RequestParam("prompt") String prompt) {
       ChatBotRequest request = new ChatBotRequest(model,
                List.of(new Message("user", prompt)),
                maxCompletions,
                temperature);

        ChatBotResponse response= restTemplate.postForObject(apiUrl, request, ChatBotResponse.class);
        return response;
    }
}
