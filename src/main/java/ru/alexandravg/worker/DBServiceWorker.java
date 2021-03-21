package ru.alexandravg.worker;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.alexandravg.service.DBService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DBServiceWorker implements DBService {
    public Connection connection = null;

    public void makeAllNecessaryActions() {
        InputStream file = null;
        try {
            file = new ClassPathResource("./init.sql").getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = this.connectDB();
        try {
            SqlScriptExecutor.executeSqlScript(file, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection connectDB() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "password"); //todo look up how to use properties
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return connection;
    }

    @Override
    public int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

//    private void createTables() throws SQLException {
//        String createCinemas = "CREATE TABLE IF NOT EXISTS cinema (id UUID PRIMARY KEY, name TEXT)";
//        String createHalls = "CREATE TABLE IF NOT EXISTS hall (id UUID PRIMARY KEY, name TEXT)";
//        String createSeats = "CREATE TABLE IF NOT EXISTS seat (id UUID PRIMARY KEY, line INTEGER, place INTEGER, taken BOOLEAN)";
//        String createReservations = "CREATE TABLE IF NOT EXISTS reservation (id UUID PRIMARY KEY, name TEXT, date TIMESTAMP WITHOUT TIME ZONE)";
//        executeUpdate(createCinemas);
//        executeUpdate(createHalls);
//        executeUpdate(createSeats);
//        executeUpdate(createReservations);
//    }
}
