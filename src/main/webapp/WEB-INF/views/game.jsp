<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple Java Web App Demo</title>
</head>
<body>
<h1>Games</h1>
<ul>
    <c:forEach items="${ requestScope.games }" var="game">
        <li><c:out value="${ game.away.name } @ ${ game.home.name }" /></li>
    </c:forEach>
</ul>
</body>
</html>