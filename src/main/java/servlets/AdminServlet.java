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
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession(true);
        String url = "";
        try {
            verifyLogin(request, currentSession);

            if (currentSession.getAttribute("loggedIn") == null) {
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                if ((int) currentSession.getAttribute("RoleID") == 1)
                    url = "HRView.jsp";
                else
                    url = "ViewRequests.jsp";

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }
    }

    private void verifyLogin(HttpServletRequest request, HttpSession session) throws SQLException {
        if ("logoff".equals(request.getParameter("action"))) {
            session.setAttribute("loggedIn", null);
            return;
        }

        if (session.getAttribute("loggedIn") == null) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            JDBC newConnection = new JDBC();
            Connection con = newConnection.connection();

            String query = String.format("SELECT Id, Password, RoleID FROM Employees WHERE email='%s'", email);
            ResultSet rs = newConnection.selectStatement(con, query);

            if (!rs.next() || !(rs.getString("Password").equals(password))) {
                request.setAttribute("error", "Incorrect email or password");

                session.setAttribute("loggedIn", null);
            } else {
                List<String[]> allRequests = new ArrayList<String[]>();
                session.setAttribute("loggedIn", true);
                session.setAttribute("EmployeeID", rs.getInt("Id"));
                session.setAttribute("RoleID", rs.getInt("RoleID"));

                String selectS = "SELECT R.StartDate, R.EndDate, S.Status FROM Requests AS R LEFT JOIN Status AS S ON R.Status = S.Id WHERE EmployeeID = " + session.getAttribute("EmployeeID") + ";";
                session.setAttribute("allRequests", newConnection.getRequestData(con, selectS));

                if ((int) session.getAttribute("RoleID") == 1) {
                    selectS= "SELECT CONCAT(E.Firstname, ' ', E.Lastname) AS Name, R.StartDate, R.EndDate FROM Requests AS R LEFT JOIN Employees AS E ON R.EmployeeID = E.Id WHERE R.Status = 2;";

                    rs = newConnection.selectStatement(con, selectS);


                    session.setAttribute("everyRequest", processRS(rs));
                }
            }
        }
    }

    private List processRS(ResultSet rs) throws SQLException {
        List<String[]> everyRequest = new ArrayList<String[]>();

        try {
            while (rs.next()) {
                String[] entry = new String[3];
                entry[0] = rs.getString("Name");
                entry[1] = rs.getDate("StartDate").toString();
                entry[2] = rs.getDate("EndDate").toString();

                everyRequest.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return everyRequest;
    }
}
