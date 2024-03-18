package com.example.movie_review.dao;

import com.example.movie_review.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<Movie> getMovies() {
        String sql = """
                select * from MOVIE;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Movie.class));
    }

    public Long createMovieAndReturnId(Movie movie) {
        String sql = """
                insert into MOVIE(name, release_year, description, director_id)
                values ( ?, ?, ?, ? );
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setInt(2, movie.getReleaseYear());
            preparedStatement.setString(3, movie.getDescription());
            preparedStatement.setLong(4, movie.getDirectorId());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void createMovie(Movie movie) {
        String sql = """
                insert into MOVIE(name, release_year, description, director_id)
                values (:name, :release_year, :description, :director_id);
                """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", movie.getName())
                .addValue("release_year", movie.getReleaseYear())
                .addValue("description", movie.getDescription())
                .addValue("director_id", movie.getDirectorId())
        );
    }

    public Optional<Movie> getMovieById(long id) {
        String sql = """
                select * from MOVIE
                where ID=?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Movie.class), id)
                )
        );
    }

    public void delete(Long id) {
        String sql = """
                delete from MOVIE
                where ID=?
                """;
        template.update(sql,id);
    }
}
