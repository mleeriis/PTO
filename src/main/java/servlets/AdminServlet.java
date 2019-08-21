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
import java.util.ArrayList;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession currentSession = request.getSession();

        try {
            JDBC newConnection = new JDBC();
            Connection con = newConnection.connection();
            String query = String.format("SELECT Id FROM Employees WHERE email='%s'", email);
            ResultSet rs = newConnection.selectStatement(con, query);

            List<String[]> allRequests = new ArrayList<String[]>();

            if (!rs.next()){
                request.setAttribute("error", "Incorrect name or password");

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
            else {
                currentSession.setAttribute("EmployeeID", rs.getInt("Id"));

                currentSession.setAttribute("allRequests", newConnection.getRequestData(con));


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

}
