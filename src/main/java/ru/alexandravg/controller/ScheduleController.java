//package ru.alexandravg.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import ru.alexandravg.service.CinemaService;
//
//import java.util.UUID;
//
//@RestController(value = "/schedule")
//public class ScheduleController {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private final ScheduleService scheduleService;
//
//    public ScheduleController(ScheduleService cinemaService) {
//        this.scheduleService = cinemaService;
//    }
//
//    @GetMapping(value = "/all")
//    public ResponseEntity<?> getAllCinemas() {
//        log.info("New request in /schedule/all");
//        return new ResponseEntity<>(scheduleService.getAllMovies(), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/in_cinema")
//    public ResponseEntity<?> getAllCinemas(@RequestParam UUID cinema) {
//        log.info("New request in /schedule/in_cinema with cinema = " + cinema);
//        return new ResponseEntity<>(scheduleService.getMoviesByCinema(cinema), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/in_hall")
//    public ResponseEntity<?> getAllCinemas(@RequestParam UUID hall) {
//        log.info("New request in /schedule/in_hall with hall = " + hall);
//        return new ResponseEntity<>(scheduleService.getMoviesByHall(hall), HttpStatus.OK);
//    }
//}
