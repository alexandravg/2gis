package ru.alexandravg.domain;

import java.util.UUID;

public class Seat {
    private UUID id;
    private Integer line;
    private Integer place;
    private Boolean taken;

    public Seat() {
    }

    public Seat(UUID id, Integer line, Integer place, Boolean taken) {
        this.id = id;
        this.line = line;
        this.place = place;
        this.taken = taken;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", line=" + line +
                ", place=" + place +
                ", taken=" + taken +
                '}';
    }
}
