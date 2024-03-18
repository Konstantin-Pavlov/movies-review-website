package com.example.movie_review.exception;

public class UserNotFoundException extends Exception{
    public  UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
