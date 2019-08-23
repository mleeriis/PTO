<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jdbc.JDBC" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession currentSession = request.getSession();
    List<String[]> everyRequest = (List<String[]>) currentSession.getAttribute("everyRequest");
%>
<html>
<head>
    <title>HR View</title>
</head>
<body>
    <a href="CreateEmployeeServlet"><button>Add Employee</button></a>
    <a href="ViewRequestServlet"><button>View My Requests</button></a>
    <a href="AdminServlet?action=logoff">
        <button>Logout</button>
    </a>

    <h2>Pending Requests</h2>
    <table border="1">
        <tr>
            <th>Employee Name</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th></th>
        </tr>
        <% for (int i = 0; i < everyRequest.size(); i++) {
        %>
        <tr>
            <td><%=everyRequest.get(i)[0]%>
            </td>
            <td><%=everyRequest.get(i)[1]%>
            </td>
            <td><%=everyRequest.get(i)[2]%>
            </td>
            <td>
                <button>Approve</button>
                <button>Deny</button>
            </td>
        </tr>
        <%}%>


    </table>
</body>
</html>
