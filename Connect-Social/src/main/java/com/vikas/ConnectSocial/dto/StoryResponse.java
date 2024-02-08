package com.vikas.ConnectSocial.dto;

import com.vikas.ConnectSocial.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryResponse {
    private int id;
    private User user;
    private String image;
    private String caption;
    private LocalDateTime createdAt;
}