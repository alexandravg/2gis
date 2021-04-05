package ru.alexandravg.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.alexandravg.domain.Cinema;
import ru.alexandravg.domain.Hall;
import ru.alexandravg.domain.Seat;
import ru.alexandravg.service.CinemaService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Class for working with cinemas in h2 DB
 */
@Component
public class CinemaServiceWorker implements CinemaService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    Connection connection = null;
    DBServiceWorker dbServiceWorker = new DBServiceWorker();

    @Override
    public List<Cinema> getAllCinemas() {
        log.info("Getting all cinemas >>> in progress");
        connection = dbServiceWorker.connectDB();
        List<Cinema> cinemas = new LinkedList<>();
        String selectAll = "SELECT * FROM cinema";
        try {
            PreparedStatement statement = connection.prepareStatement(selectAll);
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                cinemas.add(new Cinema(UUID.fromString(rows.getString("id")), rows.getString("name")));
            }
            connection.close();
            log.info("Getting all cinemas >>> success");
        } catch (SQLException throwables) {
            log.error("Getting all cinemas >>> error ", throwables);
        }

        return cinemas;
    }

    @Override
    public List<Hall> getHallsInCinema(UUID cinema) {
        log.info("Getting halls in {} cinema >>> in progress", cinema);
        String selectHallInCinema = "SELECT * FROM hall WHERE cinema_id = ?";
        connection = dbServiceWorker.connectDB();
        List<Hall> answer = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(selectHallInCinema);
            statement.setObject(1, cinema);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answer.add(new Hall(UUID.fromString(resultSet.getString("id")), resultSet.getString("name")));
            }
            connection.close();
            log.info("Getting halls in {} cinema >>> success", cinema);
        } catch (SQLException throwables) {
            log.error("Getting halls in {} cinema >>> error", cinema, throwables);
        }
        return answer;
    }

    @Override
    public List<Seat> getSeatsInHall(UUID hall) {
        log.info("Getting seats in {} hall >>> in progress", hall);

        String selectSeatsInHall = "SELECT * FROM seat WHERE hall_id = ?";
        connection = dbServiceWorker.connectDB();
        List<Seat> answer = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(selectSeatsInHall);
            statement.setObject(1, hall);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answer.add(new Seat(UUID.fromString(resultSet.getString("id")), resultSet.getInt("line"),
                        resultSet.getInt("place"), resultSet.getBoolean("taken")));
            }
            connection.close();
            log.info("Getting seats in {} hall >>> success", hall);
        } catch (SQLException throwables) {
            log.error("Getting seats in {} hall >>> error", hall, throwables);
        }

        return answer;
    }
}
