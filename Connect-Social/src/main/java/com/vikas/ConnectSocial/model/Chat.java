package com.vikas.ConnectSocial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String chatName;
    private String chatImage;
    @ManyToMany
    private List<User> users;
    private LocalDateTime timeStamp;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;
}
