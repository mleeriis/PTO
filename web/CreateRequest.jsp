<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateRequest</title>
</head>
<body>
    <a href="ViewRequests.jsp"><button>View Requests</button></a>
    <form name="createRequest" action="CreateRequestServlet" method="POST">
        <label>Start Date: <input type="date" name="startDate" required></label>
        <label>End Date: <input type="date" name="endDate" required></label>
        <input type="submit" value="Submit Request" name="submitRequest">
    </form>
</body>
</html>
