import jdbc.SQLDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

    }

    public static boolean connection() {
        SQLDatabaseConnection dbConnection = new SQLDatabaseConnection();

        try (Connection connection = DriverManager.getConnection(dbConnection.getConnectionURL())) {
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
