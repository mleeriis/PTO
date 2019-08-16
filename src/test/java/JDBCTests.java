import org.junit.*;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JDBCTests {
    @Test
    public void connectionSuccessful() {
        JDBC testConnection = new JDBC();
        assertNotNull(testConnection.connection());
    }

    @Test
    public void selectStatementSuccessful() throws SQLException {
        JDBC testConnection = new JDBC();
        assertNotNull(testConnection.selectStatement(testConnection.connection()));
    }



}
