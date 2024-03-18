package com.example.movie_review.service.impl;

import com.example.movie_review.dao.UserDao;
import com.example.movie_review.dto.UserDto;
import com.example.movie_review.exception.UserNotFoundException;
import com.example.movie_review.model.User;
import com.example.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
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
                () -> new UserNotFoundException("Can't find user with ID: " + id)
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
