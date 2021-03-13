package ru.alexandravg.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBService {
    Connection connectDB();

    int executeUpdate(String query) throws SQLException;
}
