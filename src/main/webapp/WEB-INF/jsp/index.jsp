<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <h1>Index Page</h1>
    <form action="logout" method="post">
        <input type="submit" value="logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</body>
</html>
