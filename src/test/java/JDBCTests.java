import org.junit.*;

import java.sql.Connection;
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



}
