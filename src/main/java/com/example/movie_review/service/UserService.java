package com.example.movie_review.service;

import com.example.movie_review.dto.UserDto;
import com.example.movie_review.exception.UserNotFoundException;

import java.util.List;


public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserById(int id) throws UserNotFoundException;

    void createUser(UserDto user);
}
