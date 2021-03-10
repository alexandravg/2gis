package ru.alexandravg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandravg.domain.ReservationRequest;
import ru.alexandravg.service.CinemaService;

import java.util.List;
import java.util.UUID;

@RestController(value = "/cinema")
public class CinemaController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    /**
     * Endpoint for getting info about all cinemas in DB
     */
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllCinemas() {
        log.info("New request in /all");
        return new ResponseEntity<>(cinemaService.getAllCinemas(), HttpStatus.OK);
    }

    /**
     * Endpoint for getting info about halls in cinema
     *
     * @param cinema - name of the cinema
     */
    @GetMapping(value = "/halls_in_cinema")
    public ResponseEntity<?> getCinemaHalls(@RequestParam UUID cinema) {
        log.info("New request in /halls_in_cinema with cinema=" + cinema);
        return new ResponseEntity<>(cinemaService.getHallsInCinema(cinema), HttpStatus.OK);
    }

    /**
     * Endpoint for getting info about all seats in hall
     */
    @GetMapping(value = "/hall")
    public ResponseEntity<?> getSeatsInHall(@RequestParam UUID hall) {
        log.info("New request in /hall with hall=" + hall);
        return new ResponseEntity<>(cinemaService.getSeatsInHall(hall), HttpStatus.OK);
    }

    @GetMapping(value = "/reservations")
    public ResponseEntity<?> getAllReservation() {
        log.info("New request in /reservations >> ");
        return new ResponseEntity<>(cinemaService.getAllReservations(), HttpStatus.OK);
    }

    @PostMapping(value = "/reservation")
    public ResponseEntity<?> makeReservation(@RequestBody List<ReservationRequest> reservationRequest) {
        log.info("New request in /reservation >> " + reservationRequest.toString());
        return new ResponseEntity<>(cinemaService.makeReservation(reservationRequest) ? HttpStatus.OK: HttpStatus.CONFLICT);
    }
}
