package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;

import java.util.List;


public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserById(int id) throws UserNotFoundException;

    void createUser(UserDto user);
}
