package ru.example.cinemaapi.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.example.cinemaapi.model.MovieSession;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TimetableRestControllerTest {

    @MockBean
    private TimetableRestController timetableRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReturningTimetable() throws Exception {
        given(timetableRestController.getTimetable()).willReturn(
                Arrays.asList(
                        new MovieSession("Some movie", "15:19 12.02.2019", "1:30")
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/timetable"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].movieName").value("Some movie"))
                .andExpect(jsonPath("$[0].time").value("15:19 12.02.2019"))
                .andExpect(jsonPath("$[0].duration").value("1:30"));
    }

    @Test
    public void testReturningEmptyTimetable() throws Exception {
        given(timetableRestController.getTimetable()).willReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/timetable"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));
    }
}
