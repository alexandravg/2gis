package ru.alexandravg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandravg.domain.ReservationRequest;
import ru.alexandravg.service.ReservationService;

import java.util.List;
import java.util.UUID;

@RestController(value = "/reservation")
public class ReservationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ReservationService reservationService;

    public ReservationController(ReservationService cinemaService) {
        this.reservationService = cinemaService;
    }

    /**
     * Endpoint for getting all reservations
     */
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllReservation() {
        log.info("New request in /reservation/all >> ");
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    /**
     * Endpoint for making reservation
     */
    @PostMapping
    public ResponseEntity<?> makeReservation(@RequestBody List<ReservationRequest> reservationRequest) {
        log.info("New request in /reservation >> " + reservationRequest.toString());
        return new ResponseEntity<>(reservationService.makeReservation(reservationRequest) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<?> cancelReservation(@RequestBody UUID reservationId) {
        log.info("New request in /reservation/cancel >> " + reservationId.toString());
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
