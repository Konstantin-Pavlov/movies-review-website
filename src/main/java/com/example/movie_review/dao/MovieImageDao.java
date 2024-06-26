package com.example.movie_review.dao;

import com.example.movie_review.model.MovieImage;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieImageDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(MovieImage movieImage) {
        String sql = """
                insert into  MOVIE_IMAGES(movie_id, file_name)
                values ( ?, ? );
                """;
        jdbcTemplate.update(sql, movieImage.getMovieId(), movieImage.getFileName());
    }
}
