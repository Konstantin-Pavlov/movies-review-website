package com.example.movie_review.exception;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException() {
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
