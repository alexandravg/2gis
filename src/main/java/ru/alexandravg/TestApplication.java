package ru.alexandravg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.alexandravg.worker.DBServiceWorker;

@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        DBServiceWorker dbServiceWorker = new DBServiceWorker();
        dbServiceWorker.makeAllNecessaryActions();
    }
}
