import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import ru.alexandravg.service.DBService;
import ru.alexandravg.worker.DBServiceWorker;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DBTest {
    DBServiceWorker dbService = new DBServiceWorker();
    private Connection connection = null;

    @Before
    public void openConnection() {
        dbService.makeAllNecessaryActions();
        connection = dbService.connection;
    }
    @After
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void connectionAvailable() throws SQLException {
        assertTrue(connection.isValid(1));
        assertFalse(connection.isClosed());
    }

    @Test
    public void updateCustomTable(){
        String insertData = "INSERT INTO cinema VALUES ('7a7f645e-f08e-469e-979e-4e6c2678b88a', 'AURA')";
        try {
            dbService.executeUpdate(insertData);
            System.out.println(connection.createStatement().execute("SELECT * FROM cinema"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
