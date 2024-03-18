package com.example.movie_review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCastMember {
    private Long movieId;
    private String castMemberId;
    private String role;
}
