package ru.alexandravg.worker;

import com.sun.rowset.JdbcRowSetImpl;
import org.springframework.stereotype.Component;
import ru.alexandravg.domain.Cinema;
import ru.alexandravg.domain.Hall;
import ru.alexandravg.domain.Seat;
import ru.alexandravg.service.CinemaService;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class CinemaServiceWorker implements CinemaService {
    Connection connection = null;
    DBServiceWorker dbServiceWorker = new DBServiceWorker();

    @Override
    public List<Cinema> getAllCinemas() {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cinemas;
    }

    @Override
    public List<Hall> getHallsInCinema(UUID cinema) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Seat> getSeatsInHall(UUID hall) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return answer;
    }
}
