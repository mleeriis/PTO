package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLDatabaseConnection {

    public static void main() {
        String connectionUrl =
                "jdbc:sqlserver://localhost;"
                        + "database=PaidTimeOff;"
                        + "user=a;"
                        + "password=reallyStrongPwd123;";

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connected");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}