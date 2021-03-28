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
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb"); //todo look up how to use properties
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
}
