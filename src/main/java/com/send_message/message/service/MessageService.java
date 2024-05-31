package com.send_message.message.service;

import com.send_message.message.dto.message.MessageRequest;
import com.send_message.message.dto.message.MessageResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface MessageService {
    MessageResponse messageHandle(MessageRequest messageRequest);
    MessageResponse historyMessage(String email);
}
