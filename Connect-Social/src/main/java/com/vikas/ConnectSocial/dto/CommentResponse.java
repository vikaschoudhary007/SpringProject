package com.vikas.ConnectSocial.dto;

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
public class CommentResponse {
    private int id;
    private String content;
    private User user;
    private List<User> liked;
    private LocalDateTime createdAt;
}
