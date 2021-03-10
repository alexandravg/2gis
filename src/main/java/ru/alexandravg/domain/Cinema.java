package ru.alexandravg.domain;

import java.util.UUID;

public class Cinema {
    private UUID id;
    private String name;

    public Cinema() {
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
}
