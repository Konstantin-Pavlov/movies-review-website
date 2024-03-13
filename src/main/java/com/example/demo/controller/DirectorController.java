package com.example.demo.controller;

import com.example.demo.dto.DirectorDto;
import com.example.demo.dto.MovieDto;
import com.example.demo.model.Director;
import com.example.demo.service.DirectorService;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("directors")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping("{id}")
    public ResponseEntity<?> getDirectorById(@PathVariable long id) {
        DirectorDto director = directorService.getDirectorById(id);
        if (director == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("director with id %d not found", id));
        }
        return ResponseEntity.ok(director);
    }
}
