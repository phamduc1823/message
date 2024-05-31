package com.send_message.message.service.impl.message;

import com.send_message.message.dto.message.MessageType;
import com.send_message.message.service.impl.message.SendMessageImpl.Email;
import com.send_message.message.service.impl.message.SendMessageImpl.SMS;
import com.send_message.message.service.impl.message.SendMessageImpl.WhatApp;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageFactory {
    public static final SendMessage getType(MessageType messageType) {
        switch (messageType) {
            case EMAIL:
                return new Email();
            case SMS:
                return new SMS();
            case WHATSAPP:
                return new WhatApp();
            default:
                try {
                    throw new Exception("This message type is unsupported!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
