package com.niit.restController;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.niit.Model.Chat;

@Controller
public class SockController {
	private static final Log logger = LogFactory.getLog(SockController.class);
	private final SimpMessagingTemplate brokerMessagingTemplate;

	private List<String> users = new ArrayList<String>();

	@Autowired
	public SockController(SimpMessagingTemplate messagingTemplate) {
		this.brokerMessagingTemplate = messagingTemplate;
	}

	@SubscribeMapping("/join/{username}")
	public List<String> join(@DestinationVariable("username") String username) {
		System.out.println("in " + username);
		if (!users.contains(username)) {
			users.add(username);
		}
		brokerMessagingTemplate.convertAndSend("/topic/join", username);
		return users;
	}

	@MessageMapping(value = "/chat")
	public void chatReceived(Chat chat) {
		if ("all".equals(chat.getTo())) {
			brokerMessagingTemplate.convertAndSend("/queue/chats", chat);
		} else {
			brokerMessagingTemplate.convertAndSend("/queue/chats/" + chat.getTo(), chat);
			brokerMessagingTemplate.convertAndSend("/queue/chats/" + chat.getFrom(), chat);
		}
	}
}
