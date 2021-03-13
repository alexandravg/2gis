package ru.alexandravg.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Schedule {
    private UUID id;
    private String movie;
    private LocalDateTime time;

    public Schedule() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
