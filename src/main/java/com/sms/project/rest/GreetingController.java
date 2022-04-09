package com.sms.project.rest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sms.project.entity.websockets.Message;



@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class GreetingController {
	
	private SimpMessagingTemplate messagingTemplate;
	
	public GreetingController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
    	System.out.println("Sender "+message.getSenderId());
    	System.out.println("RECEPIENT "+message.getRecipientId());
    	System.out.println("Context "+message.getContent());
    	messagingTemplate.convertAndSendToUser(
        		message.getRecipientId(),"/queue/messages",
                message);
    }

}