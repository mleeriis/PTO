<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jdbc.JDBC" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession currentSession = request.getSession();

 //   List<String[]> allRequests = new ArrayList<String[]>();

    JDBC newConnection = new JDBC();
    Connection con = newConnection.connection();


    if (con != null) {
        currentSession.setAttribute("allRequests", newConnection.getRequestData(con));
    }

    List<String[]> allRequests = (List<String[]>) currentSession.getAttribute("allRequests");
%>

<html>
<head>
    <title>View Requests</title>
</head>
<body>

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
