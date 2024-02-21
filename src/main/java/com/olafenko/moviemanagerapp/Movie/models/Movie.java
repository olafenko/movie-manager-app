package com.olafenko.moviemanagerapp.Movie.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String title;
    private String description;
    private String genre;
    private String premiere;
    private String imageUrl;
    @Column(nullable = false,updatable = false)
    private String movieCode;

    public Movie(String title, String description, String genre, String premiere, String imageUrl, String movieCode) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.premiere = premiere;
        this.imageUrl = imageUrl;
        this.movieCode = movieCode;
    }

}
