package com.vikas.ConnectSocial.dto;

import com.vikas.ConnectSocial.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private List<Integer> followers;
    private List<Integer> following;
    private List<Post> savedPosts;
}
