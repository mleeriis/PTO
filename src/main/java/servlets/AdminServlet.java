package servlets;

import jdbc.JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession currentSession = request.getSession();

        try {
            boolean loggedIn = verifyLogin(request, currentSession);

            if (!loggedIn) {
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            } else {

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("ViewRequests.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean verifyLogin(HttpServletRequest request, HttpSession session) throws SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        JDBC newConnection = new JDBC();
        Connection con = newConnection.connection();

        String query = String.format("SELECT Id, Password FROM Employees WHERE email='%s'", email);
        ResultSet rs = newConnection.selectStatement(con, query);

        if (!rs.next() || !(rs.getString("Password").equals(password))) {
            request.setAttribute("error", "Incorrect email or password");
            return false;
        } else {
            List<String[]> allRequests = new ArrayList<String[]>();
            session.setAttribute("EmployeeID", rs.getInt("Id"));

            String selectS = "SELECT R.StartDate, R.EndDate, S.Status FROM Requests AS R LEFT JOIN Status AS S ON R.Status = S.Id WHERE EmployeeID = " + session.getAttribute("EmployeeID") + ";";

            session.setAttribute("allRequests", newConnection.getRequestData(con, selectS));
            return true;
        }

    }

}
