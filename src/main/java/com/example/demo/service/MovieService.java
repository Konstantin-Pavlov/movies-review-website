package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    public List<MovieDto> getMovies() {
        List<Movie> movies = List.of(
                Movie.builder().id(1L).name("От заката до рассвета").year(2000).description("Ужасы").build(),
                Movie.builder().id(2L).name("Звездные войны: Эпизод IV – Новая надежда").year(1977).description("Фантастика").build(),
                Movie.builder().id(3L).name("Властелин колец: Братство кольца").year(2001).description("Фэнтези").build(),
                Movie.builder().id(4L).name("Назад в будущее").year(1985).description("Приключения").build(),
                Movie.builder().id(5L).name("Матрица").year(1999).description("Научная фантастика").build()
        );
        List<MovieDto> moviesDtos = new ArrayList<>();
        movies.forEach(movie -> moviesDtos.add(
                MovieDto.builder().name(movie.getName()).year(movie.getYear()).description(movie.getDescription()).build()
        ));
        return moviesDtos;
    }
}
