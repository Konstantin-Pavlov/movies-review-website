package com.example.demo.service.impl;

import com.example.demo.dao.MovieDao;
import com.example.demo.dto.MovieDto;
import com.example.demo.model.Movie;
import com.example.demo.service.DirectorService;
import com.example.demo.service.MovieService;
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
}
