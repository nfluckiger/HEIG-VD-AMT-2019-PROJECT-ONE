<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple Java Web App Demo</title>
</head>
<body>
<h1>Officials</h1>
<ul>
    <c:forEach items="${ requestScope.officials }" var="official">
        <li><c:out value="${ official.firstname } ${ official.lastname }" /></li>
    </c:forEach>
</ul>
</body>
</html>