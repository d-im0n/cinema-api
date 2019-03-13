package ru.example.cinemaapi.repositories.specifications;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.example.cinemaapi.model.MovieSession;

public interface MovieSessionSpecification {

    String getSql();
    SqlParameterSource getSqlParameterSource();
    RowMapper<MovieSession> getRowMapper();
}
