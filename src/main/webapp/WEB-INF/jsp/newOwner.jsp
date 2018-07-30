<%--
  Created by IntelliJ IDEA.
  User: ME99690
  Date: 18/07/2018
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New Owner</title>
</head>
<body>
    <form:form modelAttribute="owner" method="post">
        First Name : <form:input path="firstName"></form:input>
        <form:errors path="firstName" cssStyle="color: red"></form:errors>
        <br>
        Last Name : <form:input path="lastName"></form:input>
        <form:errors path="lastName" cssStyle="color: red"></form:errors>
        <br>
        <form:button name="submit">Create</form:button>
    </form:form>
</body>
</html>
