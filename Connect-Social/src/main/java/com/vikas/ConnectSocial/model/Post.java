package com.vikas.ConnectSocial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "caption")
    private String caption;
    @Column(name = "image")
    private String image;
    @Column(name = "video")
    private String video;
    @ManyToOne
    private User user;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @OneToMany
    private List<User> liked;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
}
