package ru.alexandravg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandravg.service.CinemaService;

import java.util.UUID;

@RestController
public class CinemaController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    /**
     * Endpoint for getting info about all cinemas in DB
     * @return json with List of Cinema objects
     */
    @GetMapping(value = "/cinema")
    public ResponseEntity<?> getAllCinemas() {
        log.info("New request in /cinema");
        return new ResponseEntity<>(cinemaService.getAllCinemas(), HttpStatus.OK);
    }

    /**
     * Endpoint for getting info about halls in cinema
     *
     * @param cinema - name of the cinema
     * @return json with List of Hall objects
     */
    @GetMapping(value = "/halls_in_cinema")
    public ResponseEntity<?> getCinemaHalls(@RequestParam UUID cinema) {
        log.info("New request in /halls_in_cinema with cinema = " + cinema);
        return new ResponseEntity<>(cinemaService.getHallsInCinema(cinema), HttpStatus.OK);
    }

    /**
     * Endpoint for getting info about all seats in hall
     * @param hall - id of the hall to look for it's seats
     * @return json with List of Seat object
     */
    @GetMapping(value = "/hall")
    public ResponseEntity<?> getSeatsInHall(@RequestParam UUID hall) {
        log.info("New request in /cinema/hall with hall = " + hall);
        return new ResponseEntity<>(cinemaService.getSeatsInHall(hall), HttpStatus.OK);
    }
}
