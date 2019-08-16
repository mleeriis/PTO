import jdbc.SQLDatabaseConnection;

import java.sql.*;

public class JDBC {

    public static void main(String[] args) {


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

    public int insertStatement(Connection con) throws SQLException {
        //INSERT INTO Requests VALUES (ID, empID, start, end, 2*)
        // *2 = StatusID = Pending

        Statement stmt = null;
        String query = "INSERT INTO Requests VALUES (2, 2020-08-17, 2020-08-18, 2);";
        try {
            stmt = con.createStatement();
            int result = stmt.executeUpdate(query);
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return -1;
    }

    public int updateStatement(Connection con) throws SQLException {
        // UPDATE table SET col=val WHERE condition

        Statement stmt = null;
        String query = "UPDATE Requests SET Status = 1 WHERE ID = 1";
        try {
            stmt = con.createStatement();
            int result = stmt.executeUpdate(query);
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return -1;
    }

    public void processResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("Firstname"));
        }
        rs.close();
    }

}
