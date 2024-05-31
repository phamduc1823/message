package com.send_message.message.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageRequest {
    @NotNull(message = "Noi dung tin nhan khong duoc de trong")
    private MessageContent messageContent;
    @NotBlank(message = "trong roi kia")
    private String recipient;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sentDate;
    @NotNull(message = "Trong dinh dang message")
    private MessageType messageType;
    private String status;
    private String description;
}
