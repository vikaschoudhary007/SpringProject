package com.vikas.ConnectSocial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String video;
    @ManyToOne
    private User user;

}
