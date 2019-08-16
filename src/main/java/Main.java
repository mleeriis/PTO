import jdbc.SQLDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

    }

    public static boolean connection() {

        try {
            SQLDatabaseConnection.main();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
