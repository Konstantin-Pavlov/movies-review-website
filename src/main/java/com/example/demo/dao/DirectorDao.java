package com.example.demo.dao;

import com.example.demo.model.Director;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DirectorDao {
    private final JdbcTemplate template;

    public Optional<Director> getDirectorById(Long id) {
        String sql = """
                select * from DIRECTOR
                where ID = ?
                """;
        return Optional.ofNullable(DataAccessUtils.singleResult(
                template.query(sql, new BeanPropertyRowMapper<>(Director.class), id)
        ));
    }
}
