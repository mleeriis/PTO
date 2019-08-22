<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form name="loginForm" action="AdminServlet" method="POST">
        <label>email*: <input type="email" id="email" name="email" required></label><br>
        <label>Password*: <input type="password" id="password" name="password" required></label>
        <input type="submit" value="Submit" name="login">
    </form>
    <h2>${error}</h2>
</body>
</html>
