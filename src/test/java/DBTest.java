import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.alexandravg.domain.Cinema;
import ru.alexandravg.domain.Hall;
import ru.alexandravg.domain.Reservation;
import ru.alexandravg.domain.ReservationRequest;
import ru.alexandravg.service.CinemaService;
import ru.alexandravg.service.ReservationService;
import ru.alexandravg.worker.CinemaServiceWorker;
import ru.alexandravg.worker.DBServiceWorker;
import ru.alexandravg.worker.ReservationServiceWorker;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DBTest {
    static DBServiceWorker dbService = new DBServiceWorker();
    private static Connection connection = null;

    @BeforeAll
    public static void setConnection() {
        connection = dbService.connectDB();
    }

    @BeforeAll
    public static void doFirstActions() {
        dbService.makeAllNecessaryActions();
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void connectionAvailable() throws SQLException {
        assertTrue(connection.isValid(1));
        assertFalse(connection.isClosed());
        connection.close();
    }

    @Test
    public void getAllCinemas() {
        CinemaService cinemaService = new CinemaServiceWorker();
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        List<Cinema> expected = new LinkedList<>();
        expected.add(new Cinema("1a7f645e-f08e-469e-979e-4e6c2678b88a", "AURA"));
        expected.add(new Cinema("3a7f645e-f08e-469e-979e-4e6c2678b88a", "ROYAL"));
        assertEquals(2, cinemas.size());
        assertArrayEquals(expected.toArray(), cinemas.toArray());
    }

    @Test
    public void getHallsByCinemaId() {
        CinemaService cinemaService = new CinemaServiceWorker();
        List<Hall> halls = cinemaService.getHallsInCinema(UUID.fromString("1a7f645e-f08e-469e-979e-4e6c2678b88a"));
        List<Hall> expected = new LinkedList<>();
        expected.add(new Hall("7a7f645e-f08e-469e-979e-4e6c2678b88a", "BLACK"));
        expected.add(new Hall("2a7f645e-f08e-469e-979e-4e6c2678b88a", "3D"));
        assertEquals(2, halls.size());
        assertArrayEquals(expected.toArray(), halls.toArray());
    }

    @Test
    public void getReservationAndSeats() {
        ReservationService reservationService = new ReservationServiceWorker();
        List<Reservation> reservations = reservationService.getAllReservations();
        List<Reservation> expected = new LinkedList<>();
        expected.add(new Reservation("42e0c9fc-7ab8-42ca-b1fa-8fd137c66f1c", "ALEXANDRA",
                LocalDateTime.parse("2020-03-22T18:13:56.720"), new LinkedList<>()));

        assertEquals(1, reservations.size());
        assertArrayEquals(expected.toArray(), reservations.toArray());
    }

    @Test
    public void getReservationByName() {
        String name = "ALEXANDRA";
        ReservationService reservationService = new ReservationServiceWorker();
        List<Reservation> reservations = reservationService.getReservationsByName(name);
        List<Reservation> expected = new LinkedList<>();
        expected.add(new Reservation("42e0c9fc-7ab8-42ca-b1fa-8fd137c66f1c", "ALEXANDRA",
                LocalDateTime.parse("2020-03-22T18:13:56.720"), new LinkedList<>()));

        assertEquals(1, reservations.size());
        assertArrayEquals(expected.toArray(), reservations.toArray());
    }

    @Test
    public void makeReservationTrue() {
        ReservationService reservationService = new ReservationServiceWorker();
        List<UUID> seats = new LinkedList<>();
        seats.add(UUID.fromString("84f09724-a429-4d91-bdde-0286a41565a1"));
        ReservationRequest reservationRequest = new ReservationRequest(
                "MAX", seats
        );
        boolean result = reservationService.makeReservation(reservationRequest);
        assertTrue(result);
    }

    @Test
    public void makeReservationFalse() {
        ReservationService reservationService = new ReservationServiceWorker();
        List<UUID> seats = new LinkedList<>();
        seats.add(UUID.fromString("84f09724-a429-4d91-bdde-0286a41565a4"));
        ReservationRequest reservationRequest = new ReservationRequest(
                "MAX", seats
        );
        boolean result = reservationService.makeReservation(reservationRequest);
        assertFalse(result);
    }

    @Test
    public void revokeReservation() {
        ReservationService reservationService = new ReservationServiceWorker();
        reservationService.cancelReservation(UUID.fromString("42e0c9fc-7ab8-42ca-b1fa-8fd137c66f1c"));

        List<Reservation> reservations = reservationService.getReservationsByName("ALEXANDRA");
        assertArrayEquals(new LinkedList<>().toArray(), reservations.toArray());
    }
}
