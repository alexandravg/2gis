package ru.alexandravg.worker;

import org.springframework.stereotype.Component;
import ru.alexandravg.domain.Cinema;
import ru.alexandravg.domain.Hall;
import ru.alexandravg.domain.Seat;
import ru.alexandravg.service.CinemaService;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

//todo here is some working code
@Component
public class CinemaServiceWorker implements CinemaService {

    @PostConstruct
    public void connectDB() throws SQLException {
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return null;
    }

    @Override
    public List<Hall> getHallsInCinema(UUID cinema) {
        return null;
    }

    @Override
    public List<Seat> getSeatsInHall(UUID hall) {
        return null;
    }
}
