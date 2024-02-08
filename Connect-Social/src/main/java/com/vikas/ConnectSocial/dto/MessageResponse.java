package com.vikas.ConnectSocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vikas.ConnectSocial.model.Chat;
import com.vikas.ConnectSocial.model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {
    private int id;
    private String content;
    private String image;
    private User user;
    private Chat chat;
    private LocalDateTime timestamp;
}
