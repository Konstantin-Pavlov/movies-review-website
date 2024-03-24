package com.example.movie_review.controller;

import com.example.movie_review.dto.UserDto;
import com.example.movie_review.exception.UserNotFoundException;
import com.example.movie_review.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    //example: http://localhost:8089/users/user?id=2
    @GetMapping("user")
    public ResponseEntity<?> getUserById(@RequestParam(name = "id", defaultValue = "1") int id) {
        return getResponseEntity(id);
    }

    //example: http://localhost:8089/users/2
    @GetMapping("{id}")
    public ResponseEntity<?> showUserById(@PathVariable int id)  {
        return getResponseEntity(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User added successfully");
    }

    private ResponseEntity<?> getResponseEntity(int id) {
        try {
            UserDto user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
