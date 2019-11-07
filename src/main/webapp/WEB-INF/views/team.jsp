<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple Java Web App Demo</title>
</head>
<body>
<h1>Team</h1>
<ul>
    <c:forEach items="${ requestScope.teams }" var="team">
        <li><c:out value="${ team.name }" /></li>
    </c:forEach>
</ul>
</body>
</html>