package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCastMember {
    private Long movieId;
    private String castMemberId;
    private String role;
}
