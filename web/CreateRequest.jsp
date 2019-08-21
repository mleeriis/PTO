<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateRequest</title>
</head>
<body>
    <form name="createRequest" action="CreateRequestServlet" method="POST">
        <label>Start Date: <input type="date" name="startDate"></label>
        <label>End Date: <input type="date" name="endDate"></label>
        <input type="submit" value="Submit Request" name="submitRequest">
    </form>
</body>
</html>
