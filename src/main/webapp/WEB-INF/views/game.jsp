<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple Java Web App Demo</title>
    <base href="${ pageContext.request.contextPath }/" />
    <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
    <link rel="stylesheet" href="../../res/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../res/assets/css/font-awesome.min.css">

    <!-- Custom styles for our template -->
    <link rel="stylesheet" href="../../res/assets/css/bootstrap-theme.css" media="screen" >
    <link rel="stylesheet" href="../../res/assets/css/main.css">
</head>
<body>
<h1>Games</h1>
<ul class="list-group">
    <c:forEach items="${ requestScope.games }" var="game">
        <li class="list-group-item"><c:out value="${ game.away.name } @ ${ game.home.name }" /></li>
    </c:forEach>
</ul>
</body>
</html>