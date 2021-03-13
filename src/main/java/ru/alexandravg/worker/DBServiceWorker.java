package ru.alexandravg.worker;

import org.springframework.stereotype.Component;
import ru.alexandravg.service.DBService;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DBServiceWorker implements DBService {
    public Connection connection = null;

    @PostConstruct
    public void makeAllNecessaryActions() {
        this.connectDB();
        try {
            this.createTables();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Connection connectDB() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test"); //todo look up how to use properties
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

    private void createTables() throws SQLException {
        String createCinemas = "CREATE TABLE cinema (ID UUID PRIMARY KEY, NAME TEXT)";
        executeUpdate(createCinemas);
    }
}
