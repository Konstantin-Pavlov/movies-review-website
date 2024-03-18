package com.example.movies_revies.service;

import com.example.movies_revies.dto.MovieDto;
import com.example.movies_revies.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies();
    void createMovie(MovieDto movieDto);
    Long createMovieAndReturnId(MovieDto movieDto);

    MovieDto getMovieById(long id) throws MovieNotFoundException;
}
