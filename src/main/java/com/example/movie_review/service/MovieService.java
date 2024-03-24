package com.example.movie_review.service;

import com.example.movie_review.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies();
    void createMovie(MovieDto movieDto);
    Long createMovieAndReturnId(MovieDto movieDto);

    MovieDto getMovieById(long id) ;

    void deleteMovie(Long id);
}
