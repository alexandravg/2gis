package ru.alexandravg.service;

import ru.alexandravg.domain.Reservation;
import ru.alexandravg.domain.ReservationRequest;

import java.util.List;
import java.util.UUID;

/**
 * Interface for working with reservations in DB
 */
public interface ReservationService {

    /**
     * Method for getting all reservations
     * @return List of Reservation objects
     */
    List<Reservation> getAllReservations();

    /**
     * Method for getting reservation by name of user who made it
     * @param name - name of user to get his reservations
     * @return List of Reservation objects
     */
    List<Reservation> getReservationsByName(String name);

    /**
     * Method for making new reservation
     * @param reservationRequest - object with username and List of Seat objects to make reservation
     * @return UUID id of new reservation OR null id there was error
     */
    UUID makeReservation(ReservationRequest reservationRequest);

    /**
     * Method for cancelling reservation
     * @param id - UUID of reservation that must be cancelled
     */
    void cancelReservation(UUID id);
}
