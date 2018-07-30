<%--
  Created by IntelliJ IDEA.
  User: ME99690
  Date: 18/07/2018
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Edit Owner</title>
</head>
<body>

    <form:form modelAttribute="owner" method="post">
        First Name : <form:input path="firstName"></form:input> <br>
        Last Name : <form:input path="lastName"></form:input> <br>
        <form:button name="submit">Update</form:button>
    </form:form>

</body>
</html>
