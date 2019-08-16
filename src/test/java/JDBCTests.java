import org.junit.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JDBCTests {
    @Test
    public void connectionSuccessful() {
        JDBC testConnection = new JDBC();
        assertNotNull(testConnection.connection());
    }

    @Test
    public void selectStatementSuccessful(){

    }



}
