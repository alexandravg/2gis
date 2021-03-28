package ru.alexandravg.domain;

import java.util.List;
import java.util.UUID;

public class ReservationRequest {
    private String name;
    private List<UUID> seatId;

    public ReservationRequest() {
    }

    public ReservationRequest(String name, List<UUID> seatId) {
        this.name = name;
        this.seatId = seatId;
    }

    public List<UUID> getSeatId() {
        return seatId;
    }

    public void setSeatId(List<UUID> seatId) {
        this.seatId = seatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
