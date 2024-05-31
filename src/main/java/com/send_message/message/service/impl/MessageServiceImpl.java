package com.send_message.message.service.impl;

import com.send_message.message.dto.message.MessageRequest;
import com.send_message.message.dto.message.MessageResponse;
import com.send_message.message.dto.message.MessageType;
import com.send_message.message.dto.message.StatusType;
import com.send_message.message.entity.MessageEntity;
import com.send_message.message.repository.MessageRepository;
import com.send_message.message.service.MessageService;
import com.send_message.message.service.impl.message.MessageFactory;
import com.send_message.message.service.impl.message.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    @Override
    public MessageResponse messageHandle(MessageRequest messageRequest) {
        sendTo();

        MessageEntity messageEntity = saveMessage(messageRequest);
        messageRepository.save(messageEntity);

        if (messageRequest.getSentDate() != null) {
            scheduleMessageSending(messageEntity);
            return new MessageResponse("running..", 200, "running message...");
        } else {
            messageRepository.save(messageEntity);
            return new MessageResponse("success", 200, "Send success fully");
        }
    }

    private void sendTo() {
        List<MessageEntity> pendingMessageEntities = messageRepository.findByStatus(StatusType.PENDING.name());
        for (MessageEntity pendingMessageEntity : pendingMessageEntities) {
            scheduleMessageSending(pendingMessageEntity);
        }
    }

    private MessageEntity saveMessage(MessageRequest messageRequest) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setRecipient(messageRequest.getRecipient());
        messageEntity.setSentDate(messageRequest.getSentDate());
        messageEntity.setMessageType(messageRequest.getMessageType().name());
        messageEntity.setSubject(messageRequest.getMessageContent().getSubject());
        messageEntity.setTo(
                convert(messageRequest.getMessageContent().getTo()));
        messageEntity.setListRecipientCC(
                convert(messageRequest.getMessageContent().getListRecipientCC()));
        messageEntity.setListRecipientBCC(
                convert(messageRequest.getMessageContent().getListRecipientBCC()));
        messageEntity.setContent(messageRequest.getMessageContent().getContent());
        messageEntity.setFiles(messageRequest.getMessageContent().getFiles());
        messageEntity.setStatus(String.valueOf(StatusType.PENDING));
        return messageEntity;
    }

    private void scheduleMessageSending(MessageEntity messageEntity) {
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime sentDate = messageEntity.getSentDate();
        Duration delay = Duration.between(timeNow, sentDate);

        long delayInSeconds = sentDate.isBefore(timeNow) ? 0 : delay.getSeconds();

        SendMessage sendMessage = MessageFactory.getType(MessageType.valueOf(messageEntity.getMessageType()));

        scheduler.schedule(() -> {
            try {
                if (messageEntity.getStatus().equals(StatusType.PENDING.name())){
                    this.saveData(StatusType.PROCESSOR, messageEntity, null);

                    var response = sendMessage.send(messageEntity);

                    if (response.getStatus() == StatusType.SUCCESS) {
                        this.saveData(StatusType.SUCCESS, messageEntity, null);
                    } else {
                        this.saveData(StatusType.FAIL, messageEntity, response.getMsg());
                    }
                }
            } catch (Exception e) {
                this.saveData(StatusType.FAIL, messageEntity, e.getMessage());
            }

        }, delayInSeconds, TimeUnit.SECONDS);
    }

    private void saveData(StatusType statusType, MessageEntity messageEntity, String msg) {
        messageEntity.setStatus(String.valueOf(statusType));
        messageEntity.setDescription(msg);
        messageRepository.save(messageEntity);
    }

    private String convert(List<String> list) {
        return list != null ? String.join(" - ", list) : null;
    }

    @Override
    public MessageResponse<List<MessageEntity>> historyMessage(String email) {
        List<MessageEntity> entity = messageRepository.findByRecipient(email);
        return new MessageResponse<>("success", 200, entity);
    }
}