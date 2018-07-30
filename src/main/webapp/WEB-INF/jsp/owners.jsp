<%--
  Created by IntelliJ IDEA.
  User: ME99690
  Date: 18/07/2018
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Owners</title>
</head>
<body>
    <table>
        <thead>
            <tr style="font-weight: bold;" bgcolor="#add8e6">
                <td>ID</td>
                <td>First Name</td>
                <td>Last Name</td>
            </tr>
        </thead>
        <c:forEach items="${owners}" var="owner" varStatus="status">
            <tr bgcolor="${status.index % 2 == 0 ? 'white' : 'lightgray'}">
                <td>${owner.id}</td>
                <td>${owner.firstName}</td>
                <td>${owner.lastName}</td>

                <td><a href="/petclinic/owners/update/${owner.id}">Güncelle</a></td>
                <td><a href="/petclinic/owners/delete/${owner.id}">Sil</a></td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${not empty message}">
        <div style="color: blue">
            ${message}
        </div>
    </c:if>

    <a href="/petclinic/owners/new">New Owner</a>
</body>
</html>
