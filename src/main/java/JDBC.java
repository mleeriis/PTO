import jdbc.SQLDatabaseConnection;

import java.sql.*;

public class JDBC {

    public static void main(String[] args) {

    }

    public Connection connection() {
        SQLDatabaseConnection dbConnection = new SQLDatabaseConnection();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbConnection.getConnectionURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
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
        }
            /*
            finally {

                if (stmt != null) {
                    stmt.close();
                }

            }

             */
        return null;
    }

    public int createUpdateDeletePTO(Connection con, String preparedStatement) throws SQLException {
        int result = -1;
        Statement stmt = null;
        String query = preparedStatement;
        try {
            stmt = con.createStatement();
            result = stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("Invalid SQL Statement");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return result;
    }

    public void processResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("Firstname"));
        }
        rs.close();
    }

    public void getEmployeeData() throws SQLException {
        try {
            Connection con = connection();
            ResultSet employeeData = selectStatement(con, "employees");

            while (employeeData.next()) {
                System.out.println(employeeData.getString("Firstname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
