package ru.example.cinemaapi.repositories.specifications;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.example.cinemaapi.model.MovieSession;

import java.time.LocalDateTime;

public class MovieSessionSpecificationForToday implements MovieSessionSpecification {

    private final String sql =
            "SELECT M.NAME, MS.DATETIME, M.DURATION " +
            "FROM MOVIE_SESSIONS MS " +
            "JOIN MOVIES M ON (MS.MOVIE_ID = M.ID AND M.STATUS = 'A') " +
            "WHERE MS.DATETIME >= :time";

    private final LocalDateTime time;

    public MovieSessionSpecificationForToday(LocalDateTime time) {
        this.time = time;
    }


    @Override
    public String getSql() {
        return this.sql;
    }

    @Override
    public SqlParameterSource getSqlParameterSource() {
        return new MapSqlParameterSource("time", time);
    }

    @Override
    public RowMapper<MovieSession> getRowMapper() {
        return (resultSet, i) -> new MovieSession(
                resultSet.getString("NAME"),
                resultSet.getTimestamp("DATETIME").toString(), // todo
                resultSet.getString("DURATION"));
    }
}
