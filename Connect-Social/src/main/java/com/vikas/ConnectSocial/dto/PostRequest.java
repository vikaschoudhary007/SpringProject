package com.vikas.ConnectSocial.dto;

import com.vikas.ConnectSocial.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    private String caption;
    private String image;
    private String video;
}
