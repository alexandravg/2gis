package ru.alexandravg.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private String name;
    private LocalDateTime date;
    private List<UUID> seats;

    public Reservation() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<UUID> getSeats() {
        return seats;
    }

    public void setSeats(List<UUID> seats) {
        this.seats = seats;
    }
}
