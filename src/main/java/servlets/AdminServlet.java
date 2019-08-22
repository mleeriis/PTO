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
        String url = "";
        try {
            verifyLogin(request, currentSession);

            if ((Boolean) currentSession.getAttribute("loggedIn")) {
                if ((int) currentSession.getAttribute("RoleID") == 1)
                    url = "HRView.jsp";
                else
                    url = "ViewRequests.jsp";

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void verifyLogin(HttpServletRequest request, HttpSession session) throws SQLException {
        if ("logoff".equals(request.getParameter("action"))) {
            session.setAttribute("loggedIn", null);
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        JDBC newConnection = new JDBC();
        Connection con = newConnection.connection();

        String query = String.format("SELECT Id, Password, RoleID FROM Employees WHERE email='%s'", email);
        ResultSet rs = newConnection.selectStatement(con, query);

        if (!rs.next() || !(rs.getString("Password").equals(password))) {
            request.setAttribute("error", "Incorrect email or password");

            session.setAttribute("loggedIn", false);
        } else {
            List<String[]> allRequests = new ArrayList<String[]>();
            session.setAttribute("EmployeeID", rs.getInt("Id"));

            session.setAttribute("RoleID", rs.getInt("RoleID"));

            String selectS = "SELECT R.StartDate, R.EndDate, S.Status FROM Requests AS R LEFT JOIN Status AS S ON R.Status = S.Id WHERE EmployeeID = " + session.getAttribute("EmployeeID") + ";";

            session.setAttribute("allRequests", newConnection.getRequestData(con, selectS));

            session.setAttribute("loggedIn", true);
        }

    }

}
