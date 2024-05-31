package com.send_message.message.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageResponse<T> {
    private String message;
    private int status;
    private T data;
}
