package com.xy.controller;

import com.xy.entity.ChatMessage;
import com.xy.service.ChatMessageService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cowdog/chat")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ChatMessageController {
	private final SimpMessagingTemplate simpMessagingTemplate;
	
    @Autowired
    ChatMessageService chatMessageSer;
    
    @MessageMapping("/send")
    public void sendMsg(ChatMessage message) throws Exception {
        chatMessageSer.save(message);
        simpMessagingTemplate.convertAndSend("/sub/" + message.getChatRoom().getId(), message);
    }
}