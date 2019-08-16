package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLDatabaseConnection {

    private static String connectionURL;

    public SQLDatabaseConnection(){
        this.connectionURL = "jdbc:sqlserver://localhost;"
                + "database=PaidTimeOff;"
                + "user=sa;"
                + "password=reallyStrongPwd123;";
    }

    public static String getConnectionURL() {
        return connectionURL;
    }

}