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
import java.util.Date;
import java.util.List;

public class CreateRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("CreateRequest.jsp");
        dispatcher.forward(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession currentSession = request.getSession();

        try {
            JDBC newConnection = new JDBC();
            Connection con = newConnection.connection();
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");

            String updateStatement = "INSERT INTO Requests VALUES ("+currentSession.getAttribute("EmployeeID")+", CAST('"+startDate+"' AS datetime),CAST('"+endDate+"' AS datetime), 2);";

            newConnection.createUpdateDeletePTO(con,updateStatement);

            String selectS = "SELECT R.StartDate, R.EndDate, S.Status FROM Requests AS R LEFT JOIN Status AS S ON R.Status = S.Id WHERE EmployeeID = " + currentSession.getAttribute("EmployeeID") + ";";

            currentSession.setAttribute("allRequests", newConnection.getRequestData(con, selectS));

            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("ViewRequests.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("login.jsp");
        }
    }
}
