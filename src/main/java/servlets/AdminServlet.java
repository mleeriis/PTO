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

public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            JDBC newConnection = new JDBC();
            Connection con = newConnection.connection();
            HttpSession currentSesssion = request.getSession();

            String query = String.format("SELECT email FROM Employees WHERE email='%s'", email);

            System.out.println(newConnection.selectStatement(con,query));
            /*if (email.equals("")){
                request.setAttribute("error", "Incorrect name or password");

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }*/
            if (newConnection.selectStatement(con, query) == null){
                request.setAttribute("error", "Incorrect name or password");

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
            else {
                response.setIntHeader("EmployeeID", 1);

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
