package com.vikas.ConnectSocial.dto;

import com.vikas.ConnectSocial.model.Message;
import com.vikas.ConnectSocial.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResponse {

    private int id;
    private String chatName;
    private String chatImage;
    private List<User> users;
    private LocalDateTime timeStamp;
    private List<Message> messages;
}
