package servlets;

import jdbc.JDBC;

import javax.management.relation.Role;
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

import static java.lang.Integer.parseInt;

public class CreateEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession currentSession = request.getSession();

        try {
            JDBC newConnection = new JDBC();
            Connection con = newConnection.connection();
            String firstName = request.getParameter("Firstname");
            String lastName = request.getParameter("Lastname");
            String email = request.getParameter("email");
            int RoleID = parseInt(request.getParameter("RoleID"));
            String password = request.getParameter("password");

            String updateStatement = "INSERT INTO Employees VALUES ('"+firstName+"', '"+lastName+"', '"+email+"', "+ RoleID +", '"+password+"');";
            System.out.println(updateStatement);
            int success = newConnection.createUpdateDeletePTO(con,updateStatement);
            System.out.println(success);
            if(success == -1){
                currentSession.setAttribute("addMsg", "Error while adding");
            }
            else{
                currentSession.setAttribute("addMsg", "Successfully Added.");
            }


            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("AddEmployee.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
