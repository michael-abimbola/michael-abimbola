package com.fontys.onlineyearbook.chatapp.Onlineyearbookchatapp.controller;

import com.fontys.onlineyearbook.chatapp.Onlineyearbookchatapp.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    //this annotation helps us to map from client to our server
    @MessageMapping("/chat.register")
    //this is an annotation for sending
    @SendTo("/topic/public")
    public Message register(@Payload Message msg, SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        simpMessageHeaderAccessor.getSessionAttributes().put("username", msg.getSender());
        return msg;
    }

    //this method for continuing the chat
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message msg){
        return msg;
    }
}
