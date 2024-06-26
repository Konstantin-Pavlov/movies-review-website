package com.example.movie_review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String email;
    private String name;
    private String password;
    private boolean enabled;
}
