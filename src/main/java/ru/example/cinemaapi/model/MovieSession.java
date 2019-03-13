package ru.example.cinemaapi.model;

public class MovieSession {

    private String movieName;
    private String time;
    private String duration;

    public MovieSession(){}

    public MovieSession(String movieName, String time, String duration) {
        this.movieName = movieName;
        this.time = time;
        this.duration = duration;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getTime() {
        return time;
    }

    public String getDuration() {
        return duration;
    }
}
