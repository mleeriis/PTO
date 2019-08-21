<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
    <form name="createEmployee" action="CreateEmployeeServlet" method="POST">
        <label>First Name: <input type="text" name="Firstname" required></label>
        <label>Last Name: <input type="text" name="Lastname" required></label>
        <label>email: <input type="email" name="email" required></label>
        <label>Role:
            <select name="RoleID" required>
                <option value="2" selected>Employee</option>
                <option value="1">HR</option>
            </select>
        </label>
        <label>Password: <input type="text" name="password" required></label>
        <input type="Submit" name="AddEmployee" value="Add Employee">
    </form>
    <h2>${addMsg}</h2>

</body>
</html>
