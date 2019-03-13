package ru.example.cinemaapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.cinemaapi.model.MovieSession;
import ru.example.cinemaapi.services.TimetableService;

import java.util.List;

@RestController
public class TimetableRestController {

    private TimetableService timetableService;

    @Autowired
    public TimetableRestController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping("/timetable")
    public List<MovieSession> getTimetable(){
        return timetableService.getCurrent();
    }
}
