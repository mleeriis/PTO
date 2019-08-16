import jdbc.SQLDatabaseConnection;

import java.sql.*;

public class JDBC {

    public static void main(String[] args) {


    }

    public Connection connection() {
        SQLDatabaseConnection dbConnection = new SQLDatabaseConnection();

        try {
            Connection connection = DriverManager.getConnection(dbConnection.getConnectionURL());
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet selectStatement(Connection con, String table) throws SQLException {


            Statement stmt = null;
            String query = "SELECT * FROM " + table + ";";
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                return rs;

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
            return null;
    }

}
