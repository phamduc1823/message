package com.send_message.message.service.impl.message.SendMessageImpl;

import com.send_message.message.entity.MessageEntity;
import com.send_message.message.dto.message.MessageResult;
import com.send_message.message.dto.message.StatusType;
import com.send_message.message.service.impl.message.SendMessage;


public class Email implements SendMessage {
    @Override
    public MessageResult send(MessageEntity messageEntity) {
        try{
            return new MessageResult(StatusType.SUCCESS, "Send email running...");
        }catch (Exception e){
            return new MessageResult(StatusType.FAIL, e.getMessage());
        }
    }
}
