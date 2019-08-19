<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<String[]> allRequests = new ArrayList<String[]>();
    String[] entry = {"asfd", "basdf", "casdf"};
    allRequests.add(entry);
    allRequests.add(entry);
    allRequests.add(entry);

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
    <% for(int i = 0; i < allRequests.size(); i++){
    %>
        <tr>
            <th><%=allRequests.get(i)[0]%></th>
            <th><%=allRequests.get(i)[1]%></th>
            <th><%=allRequests.get(i)[2]%></th>
        </tr>
    <%}%>


</table>

</body>
</html>
