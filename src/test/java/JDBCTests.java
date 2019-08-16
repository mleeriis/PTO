import org.junit.*;
import static org.junit.Assert.assertTrue;

public class JDBCTests {
    @Test
    public void connectionSuccessful() {
        JDBC testConnection = new JDBC();
        assertTrue(testConnection.connection());
    }



}
