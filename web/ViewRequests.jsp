<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jdbc.JDBC" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession currentSession = request.getSession();
    List<String[]> allRequests = (List<String[]>) currentSession.getAttribute("allRequests");
%>

<html>
<head>
    <title>View Requests</title>
</head>
<body>
    <a href="CreateRequestServlet">
        <button>Create Request</button>
    </a>
    <a href="AdminServlet?action=logoff">
        <button>Logout</button>
    </a>

    <table border="1">
        <tr>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Status</th>
        </tr>
        <% for (int i = 0; i < allRequests.size(); i++) {
        %>
        <tr>
            <td><%=allRequests.get(i)[0]%>
            </td>
            <td><%=allRequests.get(i)[1]%>
            </td>
            <td><%=allRequests.get(i)[2]%>
            </td>
        </tr>
        <%}%>


    </table>

</body>
</html>
