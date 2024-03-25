package com.example.movie_review.service.impl;

import com.example.movie_review.dao.MovieDao;
import com.example.movie_review.dto.MovieDto;
import com.example.movie_review.exception.MovieNotFoundException;
import com.example.movie_review.model.Movie;
import com.example.movie_review.service.DirectorService;
import com.example.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImp implements MovieService {
    private final MovieDao movieDao;
    private final DirectorService directorService;

    @Override
    public List<MovieDto> getMovies() {
        List<Movie> movies = movieDao.getMovies();
        List<MovieDto> moviesDtos = new ArrayList<>();
        movies.forEach(movie -> moviesDtos.add(
                MovieDto.builder()
                        .id(movie.getId())
                        .name(movie.getName())
                        .description(movie.getDescription())
                        .releaseYear(movie.getReleaseYear())
                        .director(directorService.getDirectorById(movie.getDirectorId()))
                        .build()
        ));
        return moviesDtos;
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movie.getId());
        movie.setName(movieDto.getName());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setDescription(movieDto.getDescription());
        movie.setDirectorId(movieDto.getDirector().getId());

        movieDao.createMovie(movie);
        log.info("movie created: " + movie.getName());
    }

    @Override
    public Long createMovieAndReturnId(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movie.getId());
        movie.setName(movieDto.getName());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setDescription(movieDto.getDescription());
        movie.setDirectorId(movieDto.getDirector().getId());

        log.info("movie created: " + movie.getName());

        return movieDao.createMovieAndReturnId(movie);
    }

    @Override
    public MovieDto getMovieById(long id) {
        Movie movie = movieDao.getMovieById(id)
                .orElseThrow(() -> {
                    log.error("Can't find movie with id " + id);
                    return new MovieNotFoundException("Can't find movie with id " + id);
                });
        log.info("Found movie with id " + id);
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .director(directorService.getDirectorById(movie.getDirectorId()))
                .build();
    }

    @Override
    public void deleteMovie(Long id) {
        Optional<Movie> movie = movieDao.getMovieById(id);
        if (movie.isPresent()) {
            movieDao.delete(id);
            log.info("movie deleted: " + movie.get().getName());
            return;
        }
        log.error(String.format("movie with id %d not found", id));
        throw new NoSuchElementException("Can't find movie with id " + id);
    }
}
