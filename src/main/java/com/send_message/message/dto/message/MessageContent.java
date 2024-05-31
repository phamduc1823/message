package com.send_message.message.dto.message;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageContent {
    private String subject;
    private List<String> to;
    private List<String> listRecipientCC;
    private List<String> listRecipientBCC;
    @NotNull
    private String content;
    private String files;
}
