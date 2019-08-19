import jdbc.JDBC;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class JDBCTests {
    private JDBC testConnection;
    private Connection con;

    @Before
    public void initFunction(){
        testConnection = new JDBC();
        con = testConnection.connection();
    }

    @Test
    public void connectionSuccessful() {
        assertNotNull(testConnection.connection());
    }

    @Test
    public void selectStatementSuccessful() throws SQLException {
        assertNotNull(testConnection.selectStatement(con, "employees"));
        assertNull(testConnection.selectStatement(con, "emp"));
    }

    @Test
    public void insertStatementTest() throws SQLException {
        String goodStatement = "INSERT INTO Requests VALUES (2, 2020-08-17, 2020-08-18, 2);";
        String invalidStatement = "INSERT INTO Requets VALUES (2, 2020-08-17, 2020-08-18, 2);";

        assertEquals(1, testConnection.createUpdateDeletePTO(con, goodStatement));
        assertEquals(-1, testConnection.createUpdateDeletePTO(con, invalidStatement));
    }

    @Test
    public void updateStatementTest() throws SQLException {
        String goodStatement = "UPDATE Requests SET Status = 1 WHERE ID = 3";
        String validButUselessStatement = "UPDATE Requests SET Status = 1 WHERE ID = 0";
        String invalidStatement = "UPDATE Requess SET Status = 1 WHERE ID = 0";

        assertNotEquals(-1, testConnection.createUpdateDeletePTO(con, goodStatement));
        assertNotEquals(-1, testConnection.createUpdateDeletePTO(con, validButUselessStatement));
        assertEquals(-1, testConnection.createUpdateDeletePTO(con, invalidStatement));
    }

    @Test
    public void deleteStatementTest() throws SQLException {
        String goodStatement = "DELETE FROM Requests WHERE ID=2";
        String validButUselessStatement = "DELETE FROM Requests WHERE ID=0";
        String badStatement = "DELETE FROM Requess WHERE ID=2";

        assertNotEquals(-1, testConnection.createUpdateDeletePTO(con, goodStatement));
        assertNotEquals(-1, testConnection.createUpdateDeletePTO(con, validButUselessStatement));
        assertEquals(-1, testConnection.createUpdateDeletePTO(con, badStatement));
    }

    @Test
    public void getRequestsTest(){
        try {
            testConnection.getRequestData(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
