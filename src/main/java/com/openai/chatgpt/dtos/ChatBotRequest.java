package com.openai.chatgpt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatBotRequest {

    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;
}

