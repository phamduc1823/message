package com.send_message.message.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "sent_date")
    private LocalDateTime sentDate;

    @Column(name = "message_type")
    private String messageType;

    @Column(name = "subject")
    private String subject;

    @Column(name = "[to]")
    private String to;

    @Column(name = "list_recipient_cc")
    private String listRecipientCC;

    @Column(name = "list_recipient_bcc")
    private String listRecipientBCC;

    @Column(name = "content")
    private String content;

    @Column(name = "files")
    private String files;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
