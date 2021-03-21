import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import ru.alexandravg.domain.Cinema;
import ru.alexandravg.domain.Hall;
import ru.alexandravg.service.CinemaService;
import ru.alexandravg.service.DBService;
import ru.alexandravg.worker.CinemaServiceWorker;
import ru.alexandravg.worker.DBServiceWorker;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

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

    @After
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

}
