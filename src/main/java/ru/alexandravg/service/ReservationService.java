package ru.alexandravg.service;

import ru.alexandravg.domain.Reservation;
import ru.alexandravg.domain.ReservationRequest;

import java.util.List;
import java.util.UUID;

public interface ReservationService {
    List<Reservation> getAllReservations();

    Boolean makeReservation(List<ReservationRequest> reservationRequest);

    void cancelReservation(UUID id);
}
