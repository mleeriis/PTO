import jdbc.JDBC;
import org.junit.Test;
import servlets.ViewRequests;

public class ViewRequestsTest {

    @Test
    public void testConnection() {
        ViewRequests viewTest = new ViewRequests();
        viewTest.connectToDatabase();
    }
}
