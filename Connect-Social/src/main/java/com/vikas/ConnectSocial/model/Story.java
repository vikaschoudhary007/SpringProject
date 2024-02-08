package com.vikas.ConnectSocial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "story")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    private String image;
    private String caption;
    private LocalDateTime createdAt;
}
