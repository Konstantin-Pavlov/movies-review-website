package com.example.movie_review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieImage {
    private long id;
    private long movieId;
    private String fileName;
}
