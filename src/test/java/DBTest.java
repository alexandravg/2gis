import org.junit.After;
import org.junit.Before;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.alexandravg.domain.Cinema;
import ru.alexandravg.domain.Hall;
import ru.alexandravg.domain.Reservation;
import ru.alexandravg.domain.ReservationRequest;
import ru.alexandravg.service.CinemaService;
import ru.alexandravg.service.DBService;
import ru.alexandravg.service.ReservationService;
import ru.alexandravg.worker.CinemaServiceWorker;
import ru.alexandravg.worker.DBServiceWorker;
import ru.alexandravg.worker.ReservationServiceWorker;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBTest {
    DBServiceWorker dbService = new DBServiceWorker();
    private Connection connection = null;

    @Before
    public void setConnection(){
        connection = dbService.connectDB();
    }

    @Test
    public void doFirstActions() {
        dbService.makeAllNecessaryActions();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void connectionAvailable() throws SQLException {
        assertTrue(connection.isValid(1));
        assertFalse(connection.isClosed());
        connection.close();
    }

    @Test
    public void getAllCinemas(){
        CinemaService cinemaService = new CinemaServiceWorker();
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        System.out.println(cinemas.size());
        cinemas.forEach(System.out::println);
        assertEquals(2,cinemas.size());
    }

    @Test
    public void getHallsByCinemaId(){
        CinemaService cinemaService = new CinemaServiceWorker();
        List<Hall> halls = cinemaService.getHallsInCinema(UUID.fromString("1a7f645e-f08e-469e-979e-4e6c2678b88a"));
        System.out.println(halls.size());
        halls.forEach(System.out::println);
        assertEquals(2, halls.size());
    }

    @Test
    public void getReservationAndSeats(){
        ReservationService reservationService = new ReservationServiceWorker();
        List<Reservation> reservations = reservationService.getAllReservations();
        System.out.println(reservations.size());
        reservations.forEach(System.out::println);
    }

    @Test
    public void getReservationByName(){
        String name = "ALEXANDRA";
        ReservationService reservationService = new ReservationServiceWorker();
        List<Reservation> reservations = reservationService.getReservationsByName(name);
        System.out.println(reservations.size());
        reservations.forEach(System.out::println);
    }

    @Test
    public void makeReservation() {
        ReservationService reservationService = new ReservationServiceWorker();
        List<UUID> seats = new LinkedList<>();
        seats.add(UUID.fromString("84f09724-a429-4d91-bdde-0286a41565a1"));
        ReservationRequest reservationRequest = new ReservationRequest(
                "MAX", seats
        );

        boolean result = reservationService.makeReservation(reservationRequest);
        System.out.println(result);
    }

}
