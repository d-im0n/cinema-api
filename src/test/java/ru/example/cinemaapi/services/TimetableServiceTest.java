package ru.example.cinemaapi.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.example.cinemaapi.model.MovieSession;
import ru.example.cinemaapi.repositories.MovieSessionRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TimetableServiceTest {

    @Mock
    private MovieSessionRepository movieSessionRepository;

    private TimetableService timetableService;

    @Before
    public void setUp(){
        timetableService = new TimetableService(movieSessionRepository);
    }

    @Test
    public void testReturningTimetable() {

        given(movieSessionRepository.query(any())).willReturn(
                Arrays.asList(
                        new MovieSession("Some movie", "12:15 21.02.2019", "2:00")));

        List<MovieSession> sessions = timetableService.getCurrent();

        assertThat(sessions.size()).isEqualTo(1);
        assertThat(sessions.get(0).getMovieName()).isEqualTo("Some movie");
        assertThat(sessions.get(0).getTime()).isEqualTo("12:15 21.02.2019");
        assertThat(sessions.get(0).getDuration()).isEqualTo("2:00");
    }

    @Test
    public void testReturningEmptyTimetable() {
        given(movieSessionRepository.query(any())).willReturn(Collections.emptyList());

        List<MovieSession> sessions = timetableService.getCurrent();

        assertThat(sessions.size()).isEqualTo(0);
    }
}
