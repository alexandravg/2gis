package ru.alexandravg.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.alexandravg.service.DBService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBServiceWorker implements DBService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public Connection connection = null;

    @Override
    public Connection connectDB() {
        log.info("Connecting to DB >>> in progress");
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb");
            log.info("Connecting to DB >>> success");
        } catch (SQLException throwable) {
            log.error("Connecting to DB >>> error ", throwable);
        }
        return connection;
    }

    @Override
    public void makeAllNecessaryActions() {
        log.info("Init DB >>> in progress");
        InputStream file = null;
        try {
            file = new ClassPathResource("./init.sql").getInputStream();
            log.info("Init DB >>> file successfully read");
        } catch (IOException e) {
            log.error("Init DB >>> error reading init file", e);
        }
        Connection connection = this.connectDB();
        try {
            SqlScriptExecutor.executeSqlScript(file, connection);
            log.info("Init DB >>> script successfully executed");
        } catch (Exception e) {
            log.error("Init DB >>> error executing init script", e);
        }
    }
}
