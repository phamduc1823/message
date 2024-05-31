package com.send_message.message.repository;

import com.send_message.message.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long>, JpaSpecificationExecutor<MessageEntity> {

    List<MessageEntity> findByRecipient(String recipient);

    List<MessageEntity> findByStatus(String status);
}
