package ru.example.cinemaapi.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.example.cinemaapi.model.MovieSession;
import ru.example.cinemaapi.repositories.specifications.MovieSessionSpecification;

import java.util.List;

@Repository
public class MovieSessionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MovieSessionRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MovieSession> query(MovieSessionSpecification specification){

        return jdbcTemplate.query(specification.getSql(),
                specification.getSqlParameterSource(),
                specification.getRowMapper());
    }
}
