package ru.alexandravg.service;

import ru.alexandravg.domain.*;

import java.util.List;
import java.util.UUID;

/**
 * Interface to work with cinemas in DB
 */
public interface CinemaService {
    /**
     * Method for getting all cinemas in db
     * @return List of Cinema objects
     */
    List<Cinema> getAllCinemas();

    /**
     * Method for getting halls in exact cinema
     * @param cinema - id of cinema to look for it's halls
     * @return List of Hall objects
     */
    List<Hall> getHallsInCinema(UUID cinema);

    /**
     * Method for getting seats in exact hall
     * @param hall - id of hall to look for it's seats
     * @return list of Seat objects
     */
    List<Seat> getSeatsInHall(UUID hall);
}
