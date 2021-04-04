package ru.alexandravg.worker;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.alexandravg.domain.Cinema;
import ru.alexandravg.domain.Hall;
import ru.alexandravg.domain.Reservation;
import ru.alexandravg.domain.ReservationRequest;
import ru.alexandravg.service.ReservationService;
import sun.font.CreatedFontTracker;

import javax.management.Query;
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
        List<Reservation> reservations = new LinkedList<>();
        String selectAll = "SELECT * FROM reservation";
        connection = dbServiceWorker.connectDB();
        try {
            PreparedStatement selectAllQuery = connection.prepareStatement(selectAll);
            reservations = executeReservationQuery(selectAllQuery);
            connection.close();
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
            connection.close();
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
            PreparedStatement selectSeatForCheckQuery = connection.prepareStatement(selectSeatForCheck);
            for (UUID id : reservationRequest.getSeatId()) {
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

            PreparedStatement insertReservationSeatsQuery = connection.prepareStatement(insertReservationSeats);
            for (UUID seatId : reservationRequest.getSeatId()) {
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
        String deleteReservationSeat = "DELETE FROM reservation_seats WHERE reservation_id = ?";
        String deleteReservation = "DELETE FROM reservation where id = ?";
        String updateSeatStatus = "UPDATE seat SET taken = 'false' WHERE id = ?";
        String selectReservationSeats = "SELECT * FROM reservation_seats WHERE reservation_id = ?";

        connection = dbServiceWorker.connectDB();

        try {
            PreparedStatement selectReservationSeatsQuery = connection.prepareStatement(selectReservationSeats);
            selectReservationSeatsQuery.setObject(1, id);
            ResultSet resultSet = selectReservationSeatsQuery.executeQuery();
            List<UUID> seats = new LinkedList<>();
            while (resultSet.next()) {
                seats.add(resultSet.getObject("seat_id", UUID.class));
            }

            PreparedStatement deleteReservationSeatQuery = connection.prepareStatement(deleteReservationSeat);
            deleteReservationSeatQuery.setObject(1, id);
            deleteReservationSeatQuery.executeUpdate();

            PreparedStatement deleteReservationQuery = connection.prepareStatement(deleteReservation);
            deleteReservationQuery.setObject(1, id);
            deleteReservationQuery.executeUpdate();

            PreparedStatement updateSeatStatusQuery = connection.prepareStatement(updateSeatStatus);
            for (UUID seatId : seats) {
                updateSeatStatusQuery.setObject(1, seatId);
                updateSeatStatusQuery.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Reservation> executeReservationQuery(PreparedStatement statement) throws SQLException {
        ResultSet rows = statement.executeQuery();
        List<Reservation> reservations = new LinkedList<>();
        String selectSeats = "SELECT * FROM reservation_seats WHERE reservation_id = ?";
        while (rows.next()) {
            Reservation reservation = new Reservation(UUID.fromString(rows.getString("id")),
                    rows.getString("name"),
                    rows.getObject("date", LocalDateTime.class),
                    new LinkedList<>());
            PreparedStatement getSeats = connection.prepareStatement(selectSeats);
            getSeats.setObject(1, reservation.getId());
            ResultSet resultSet = getSeats.executeQuery();
            while (resultSet.next()) {
                reservation.getSeats().add(resultSet.getObject("seat_id", UUID.class));
            }
            reservations.add(reservation);
        }
        return reservations;
    }
}
