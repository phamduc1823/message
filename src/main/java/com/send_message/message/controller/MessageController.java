package com.send_message.message.controller;

import com.send_message.message.dto.message.MessageRequest;
import com.send_message.message.dto.message.MessageResponse;
import com.send_message.message.service.impl.MessageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    MessageServiceImpl messageServiceImpl;

    @PostMapping("/send-message")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody @Valid MessageRequest messageRequest){
        return ResponseEntity.ok(messageServiceImpl.messageHandle(messageRequest));
    }

    @PostMapping("/history-message/{email}")
    public ResponseEntity<MessageResponse> historyMessage(@PathVariable String email){
        return ResponseEntity.ok(messageServiceImpl.historyMessage(email));
    }

}
