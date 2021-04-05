package ru.alexandravg.service;

import java.sql.Connection;

/**
 * Interface for working with DB
 */
public interface DBService {

    /**
     * Method for connecting to db
     * @return Connection object
     */
    Connection connectDB();

    /**
     * Method for making actions on start, e.g. connect DB, init DB, close connection
     */
    void makeAllNecessaryActions();
}
