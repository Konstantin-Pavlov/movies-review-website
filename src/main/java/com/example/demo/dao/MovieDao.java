package com.example.demo.dao;

import com.example.demo.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieDao {
    private final JdbcTemplate template;

    public List<Movie> getMovies() {
        String sql = """
                select * from MOVIE;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Movie.class));
    }
}
