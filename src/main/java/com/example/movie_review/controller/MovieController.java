package com.example.movie_review.controller;

import com.example.movie_review.dto.MovieDto;
import com.example.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //    @RequestMapping(name = "id", method = RequestMethod.GET)
    @GetMapping("{id}")
    public ResponseEntity<?> getMovieById(@PathVariable long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
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

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
//        if (movieService.deleteMovie(id)) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Movie with id %s deleted successfully", id));

}}
