package com.example.movies_revies.service.impl;

import com.example.movies_revies.dao.MovieDao;
import com.example.movies_revies.dto.MovieDto;
import com.example.movies_revies.exception.MovieNotFoundException;
import com.example.movies_revies.model.Movie;
import com.example.movies_revies.service.DirectorService;
import com.example.movies_revies.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public Long createMovieAndReturnId(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movie.getId());
        movie.setName(movieDto.getName());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setDescription(movieDto.getDescription());
        movie.setDirectorId(movieDto.getDirector().getId());

        return  movieDao.createMovieAndReturnId(movie);
    }

    @Override
    public MovieDto getMovieById(long id) throws MovieNotFoundException {
         Movie movie = movieDao.getMovieById(id)
                 .orElseThrow(()-> new MovieNotFoundException("Can't find movie with id " + id));

         return MovieDto.builder()
                 .id(movie.getId())
                 .name(movie.getName())
                 .releaseYear(movie.getReleaseYear())
                 .description(movie.getDescription())
                 .director(directorService.getDirectorById(movie.getDirectorId()))
                 .build();
    }
}
