package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(user ->
                dtos.add(UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .password(user.getPassword())
                        .build()
                )
        );
        return dtos;
    }

//    @SneakyThrows // try catch -> e.printStackTrace()
    @Override
    public UserDto getUserById(int id) throws UserNotFoundException {
        User user = userDao.getUserById(id).orElseThrow(
                () -> new UserNotFoundException("Can't find user wit ID: " + id)
        );
        return UserDto.builder().
                id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void createUser(UserDto user) {
        userDao.createUser(user);
    }
}
