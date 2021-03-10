package ru.alexandravg.service;

import org.springframework.stereotype.Component;
import ru.alexandravg.domain.*;

import java.util.List;
import java.util.UUID;

@Component
public interface CinemaService {
    List<Cinema> getAllCinemas();

    List<Hall> getHallsInCinema(UUID cinema);

    List<Seat> getSeatsInHall(UUID hall);

    List<Reservation> getAllReservations();

    Boolean makeReservation(List<ReservationRequest> reservationRequest);
}
