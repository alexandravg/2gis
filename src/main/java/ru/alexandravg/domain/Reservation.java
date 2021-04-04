package ru.alexandravg.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private String name;
    private LocalDateTime date;
    private List<UUID> seats;

    public Reservation() {
    }

    public Reservation(UUID id, String name, LocalDateTime date, List<UUID> seats) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.seats = seats;
    }

    public Reservation(String id, String name, LocalDateTime date, List<UUID> seats) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.date = date;
        this.seats = seats;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", seats=" + seats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date);
    }
}
