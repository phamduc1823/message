package com.send_message.message.service.impl.message;

import com.send_message.message.entity.MessageEntity;
import com.send_message.message.dto.message.MessageResult;

public interface SendMessage {
    MessageResult send(MessageEntity messageEntity);
}
