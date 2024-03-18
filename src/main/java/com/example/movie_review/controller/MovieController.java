package com.example.movie_review.controller;

import com.example.movie_review.dto.MovieDto;
import com.example.movie_review.exception.MovieNotFoundException;
import com.example.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMovieById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(movieService.getMovieById(id));
        } catch (MovieNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("add-and-return-id")
    public ResponseEntity<?> createAndReturnId(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.createMovieAndReturnId(movieDto));
    }

    @PostMapping("add")
    public HttpStatus create(@RequestBody MovieDto movieDto) {
        movieService.createMovie(movieDto);
        return HttpStatus.OK;
    }
}
