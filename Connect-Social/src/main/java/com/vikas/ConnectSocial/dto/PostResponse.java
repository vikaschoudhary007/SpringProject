package com.vikas.ConnectSocial.dto;

import com.vikas.ConnectSocial.model.Comment;
import com.vikas.ConnectSocial.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private int id;
    private String caption;
    private String image;
    private String video;
    private User user;
    private LocalDate createdAt;
    private List<User> liked;
    private List<Comment> comments;
}
