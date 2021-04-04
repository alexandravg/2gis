package ru.alexandravg.domain;

import java.util.Objects;
import java.util.UUID;

public class Cinema {
    private UUID id;
    private String name;

    public Cinema() {
    }

    public Cinema(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cinema(String id, String name) {
        this.id = UUID.fromString(id);
        this.name = name;
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

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(id, cinema.id) && Objects.equals(name, cinema.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
