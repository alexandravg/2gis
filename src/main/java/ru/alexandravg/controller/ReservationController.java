package ru.alexandravg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexandravg.domain.ReservationRequest;
import ru.alexandravg.service.ReservationService;

import java.util.UUID;

@RestController
public class ReservationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ReservationService reservationService;

    public ReservationController(ReservationService cinemaService) {
        this.reservationService = cinemaService;
    }

    /**
     * Endpoint for getting all reservations
     * @return json with List of Reservation objects
     */
    @GetMapping(value = "/reservation")
    public ResponseEntity<?> getAllReservation() {
        log.info("New request in /reservation/all >> ");
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    /**
     * Endpoint for making reservation
     * @param reservationRequest - json in body, ReservationRequest object
     * @return json with UUID of made reservation OR 409 code if the seat is already taken
     */
    @PostMapping(value = "/reservation")
    public ResponseEntity<?> makeReservation(@RequestBody ReservationRequest reservationRequest) {
        log.info("New request in /reservation >> " + reservationRequest.toString());
        UUID result = reservationService.makeReservation(reservationRequest);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Endpoint for cancelling reservation
     * @param reservationId  - json with id of reservation that must be cancelled
     */
    @PutMapping(value = "/reservation")
    public ResponseEntity<?> cancelReservation(@RequestBody UUID reservationId) {
        log.info("New request in /reservation/cancel >> " + reservationId.toString());
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
