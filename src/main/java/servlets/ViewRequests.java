package servlets;

import jdbc.JDBC;
import java.sql.Connection;


public class ViewRequests {

    public void connectToDatabase(){
        JDBC testConnection = new JDBC();
        Connection con = testConnection.connection();

        if (con != null) {

            System.out.println("success");
        } else {

            System.out.println("error");
        }

    }
}
