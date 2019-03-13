package ru.example.cinemaapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.cinemaapi.model.MovieSession;
import ru.example.cinemaapi.repositories.MovieSessionRepository;
import ru.example.cinemaapi.repositories.specifications.MovieSessionSpecificationForToday;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimetableService {

    private final MovieSessionRepository movieSessionRepository;

    @Autowired
    public TimetableService(MovieSessionRepository movieSessionRepository) {
        this.movieSessionRepository = movieSessionRepository;
    }

    public List<MovieSession> getCurrent() {
        return movieSessionRepository.query(new MovieSessionSpecificationForToday(LocalDateTime.now()));
    }
}
