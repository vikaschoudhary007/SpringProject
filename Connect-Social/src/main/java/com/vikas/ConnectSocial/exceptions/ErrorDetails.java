package com.vikas.ConnectSocial.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {

    private String message;
    private String error;
    private LocalDateTime timestamp;
}
