package ru.alexandravg.domain;

import java.util.Objects;
import java.util.UUID;

public class Hall {
    private UUID id;
    private String name;

    public Hall() {
    }

    public Hall(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hall(String id, String name) {
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
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return Objects.equals(id, hall.id) && Objects.equals(name, hall.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
