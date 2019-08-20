package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {

    public static void main(String[] args) {
        JDBC testConnection = new JDBC();
        Connection con = testConnection.connection();

        if (con != null) {

            System.out.println("success");
        } else {

            System.out.println("error");
        }

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

    public ResultSet selectStatement(Connection con, String inputQ) throws SQLException {

        Statement stmt = null;
        String query = inputQ;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
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
        return rs;
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

    public List getRequestData(Connection con) throws SQLException {
        /*
        HR View Query =
        SELECT CONCAT(E.Firstname, ' ', E.Lastname) AS EmployeeName, R.StartDate, R.EndDate, S.Status FROM Requests AS R
        LEFT JOIN Status AS S ON R.Status = S.Id
        LEFT JOIN Employees AS E ON E.Id = R.EmployeeID
        WHERE R.EmployeeID = 1;
        */

        List<String[]> allRequestsForEmployee = new ArrayList<String[]>();
        Statement stmt = null;
      //  String query = "SELECT R.StartDate, R.EndDate, S.Status FROM Requests AS R LEFT JOIN Status AS S ON R.Status = S.Id WHERE EmployeeID = 1;";

        String query = "SELECT email, Firstname, Lastname FROM Employees WHERE email='x'";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // String startDate, endDate, status;
            String[] entry = new String[3];

            while (rs.next()) {
                entry[0] = rs.getDate("StartDate").toString();
                entry[1] = rs.getDate("EndDate").toString();
                entry[2] = rs.getString("Status");

                allRequestsForEmployee.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return allRequestsForEmployee;
    }
}
