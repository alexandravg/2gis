package ru.alexandravg.domain;

import java.util.UUID;

public class ReservationRequest {
    private UUID seatId;

    public ReservationRequest() {
    }

    public UUID getSeatId() {
        return seatId;
    }

    public void setSeatId(UUID seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "seatId=" + seatId +
                '}';
    }
}
