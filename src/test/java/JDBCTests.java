import org.junit.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class JDBCTests {
    @Test
    public void connectionSuccessful() {
        JDBC testConnection = new JDBC();
        assertNotNull(testConnection.connection());
    }

    @Test
    public void selectStatementSuccessful() throws SQLException {
        JDBC testConnection = new JDBC();
        Connection con = testConnection.connection();
        assertNotNull(testConnection.selectStatement(con, "employees"));
        assertNull(testConnection.selectStatement(con, "emp"));
    }

    @Ignore
    @Test
    public void processResultSetTest() throws SQLException {
        JDBC testConnection = new JDBC();
        Connection con = testConnection.connection();
        ResultSet rs = testConnection.selectStatement(con, "employees");

        testConnection.processResultSet(rs);

    }

    @Ignore
    @Test
    public void getEmployeeDataTest() {
        JDBC testConnection = new JDBC();

        try {
            testConnection.getEmployeeData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertStatementTest() throws SQLException {
        JDBC testConnection = new JDBC();
        Connection con = testConnection.connection();
        String goodStatement = "INSERT INTO Requests VALUES (2, 2020-08-17, 2020-08-18, 2);";
        String invalidStatement = "INSERT INTO Requets VALUES (2, 2020-08-17, 2020-08-18, 2);";
        assertEquals(1, testConnection.createUpdateDeletePTO(con, goodStatement));
        assertEquals(-1, testConnection.createUpdateDeletePTO(con, invalidStatement));
    }

    @Test
    public void updateStatementTest() throws SQLException {
        JDBC testConnection = new JDBC();
        Connection con = testConnection.connection();
        String goodStatement = "UPDATE Requests SET Status = 1 WHERE ID = 3";
        String validButUselessStatement = "UPDATE Requests SET Status = 1 WHERE ID = 0";
        String invalidStatement = "UPDATE Requess SET Status = 1 WHERE ID = 0";
        assertNotEquals(-1, testConnection.createUpdateDeletePTO(con, goodStatement));
        assertNotEquals(-1, testConnection.createUpdateDeletePTO(con, validButUselessStatement));
        assertEquals(-1, testConnection.createUpdateDeletePTO(con, invalidStatement));
    }

    @Test
    public void deleteStatementTest() throws SQLException {
        JDBC testConnection = new JDBC();
        Connection con = testConnection.connection();
        assertEquals(1, testConnection.createUpdateDeletePTO(con, "DELETE FROM Requests WHERE ID=2"));
    }
}
