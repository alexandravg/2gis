package ru.alexandravg.worker;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.alexandravg.domain.Cinema;
import ru.alexandravg.domain.Hall;
import ru.alexandravg.domain.Reservation;
import ru.alexandravg.domain.ReservationRequest;
import ru.alexandravg.service.ReservationService;
import sun.font.CreatedFontTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class ReservationServiceWorker implements ReservationService {

    Connection connection = null;
    DBServiceWorker dbServiceWorker = new DBServiceWorker();

    @Override
    public List<Reservation> getAllReservations() {
        connection = dbServiceWorker.connectDB();
        List<Reservation> reservations = new LinkedList<>();
        String selectAll = "SELECT * FROM reservation";
        try {
            PreparedStatement selectAllQuery = connection.prepareStatement(selectAll);
            reservations = executeReservationQuery(selectAllQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByName(String name) {
        connection = dbServiceWorker.connectDB();
        List<Reservation> reservations = new LinkedList<>();
        String selectByName = "SELECT * FROM reservation where name = ?";
        try {
            PreparedStatement selectByNameQuery = connection.prepareStatement(selectByName);
            selectByNameQuery.setString(1, name);
            reservations = executeReservationQuery(selectByNameQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reservations;
    }

    @Override
    public Boolean makeReservation(ReservationRequest reservationRequest) {
        String insertReservation = "INSERT INTO reservation VALUES (?, ?, ?)";
        String insertReservationSeats = "INSERT INTO reservation_seats VALUES (?, ?)";
        String updateSeatStatus = "UPDATE seat SET taken = 'true' where id = ?";
        String selectSeatForCheck = "SELECT * FROM seat WHERE id = ?";

        connection = dbServiceWorker.connectDB();
        try {
            for (UUID id : reservationRequest.getSeatId()) {
                PreparedStatement selectSeatForCheckQuery = connection.prepareStatement(selectSeatForCheck);
                selectSeatForCheckQuery.setObject(1, id);
                ResultSet resultSet = selectSeatForCheckQuery.executeQuery();
                while (resultSet.next()) {
                    boolean taken = resultSet.getBoolean("taken");
                    if (taken) return false;
                }
                PreparedStatement updateSeatStatusQuery = connection.prepareStatement(updateSeatStatus);
                updateSeatStatusQuery.setObject(1, id);
                updateSeatStatusQuery.executeUpdate();
            }

            UUID id = UUID.randomUUID();
            PreparedStatement insertReservationQuery = connection.prepareStatement(insertReservation);
            insertReservationQuery.setObject(1, id);
            insertReservationQuery.setObject(2, reservationRequest.getName());
            insertReservationQuery.setObject(3, LocalDateTime.now());
            insertReservationQuery.executeUpdate();

            for (UUID seatId : reservationRequest.getSeatId()) {
                PreparedStatement insertReservationSeatsQuery = connection.prepareStatement(insertReservationSeats);
                insertReservationSeatsQuery.setObject(1, id);
                insertReservationSeatsQuery.setObject(2, seatId);
                insertReservationSeatsQuery.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void cancelReservation(UUID id) {

    }

    private List<Reservation> executeReservationQuery(PreparedStatement statement) throws SQLException {
        ResultSet rows = statement.executeQuery();
        List<Reservation> reservations = new LinkedList<>();
        String selectSeats = "SELECT * FROM reservation_seats WHERE reservation_id = ?";
        while (rows.next()) {
            Reservation reservation = new Reservation(UUID.fromString(rows.getString("id")),
                    rows.getString("name"),
                    rows.getObject("date", LocalDateTime.class),
                    new LinkedList<UUID>());
            PreparedStatement getSeats = connection.prepareStatement(selectSeats);
            getSeats.setObject(1, reservation.getId());
            ResultSet resultSet = getSeats.executeQuery();
            while (resultSet.next()) {
                reservation.getSeats().add(resultSet.getObject("seat_id", UUID.class));
            }
            reservations.add(reservation);
        }
        connection.close();
        return reservations;
    }
}
