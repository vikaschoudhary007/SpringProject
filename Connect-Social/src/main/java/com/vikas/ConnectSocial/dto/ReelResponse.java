package com.vikas.ConnectSocial.dto;

import com.vikas.ConnectSocial.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReelResponse {
    private int id;
    private String title;
    private String video;
    private User user;
}
