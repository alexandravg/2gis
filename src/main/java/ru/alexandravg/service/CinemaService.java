package ru.alexandravg.service;

import ru.alexandravg.domain.*;

import java.util.List;
import java.util.UUID;

public interface CinemaService {
    List<Cinema> getAllCinemas();

    List<Hall> getHallsInCinema(UUID cinema);

    List<Seat> getSeatsInHall(UUID hall);
}
