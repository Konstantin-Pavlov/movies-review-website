package com.example.movie_review.dao;

import com.example.movie_review.dto.UserDto;
import com.example.movie_review.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getUsers() {
        String sql = """
                    select * from USER_TABLE;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserById(int id) {
        String sql = """
                select * from USER_TABLE
                where id = ?;
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                ));
    }

    public void createUser(UserDto user) {
        String sql = """
                    insert into USER_TABLE(EMAIL, NAME, PASSWORD, ENABLED)
                    values(:email, :name, :password, :enabled);
                """;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", user.getEmail())
                .addValue("name", user.getName())
                .addValue("password", user.getPassword())
                .addValue("enabled", user.isEnabled());
        namedParameterJdbcTemplate.update(sql, params);

    }
}
