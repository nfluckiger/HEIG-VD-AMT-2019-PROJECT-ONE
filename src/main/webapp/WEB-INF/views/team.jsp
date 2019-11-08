<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple Java Web App Demo</title>
    <base href="${ pageContext.request.contextPath }/" />
    <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
    <link rel="stylesheet" href="../../res/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../res/assets/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

    <!-- Custom styles for our template -->
    <link rel="stylesheet" href="../../res/assets/css/bootstrap-theme.css" media="screen" >
    <link rel="stylesheet" href="../../res/assets/css/main.css">
</head>
<body>
<h1>Team</h1>
<button id="create" type="button" class="btn btn-primary">Add a team</button>
<ul class="list-group">
    <c:forEach items="${ requestScope.teams }" var="team">
        <li class="list-group-item"><c:out value="${ team.name }" /></li>
    </c:forEach>
</ul>
<script type="text/javascript">
    $("#create").click(() => {
        $("#create").after(
            '<form method="post" action="${ pageContext.request.contextPath }/teams">' +
                '<div class="form-group">' +
                    '<label for="name">Name</label>' +
                    '<input type="text" class="form-control" id="name" name="name" />' +
                '</div>' +
                '<div class="form-group">' +
                    '<label for="address">Address</label>' +
                    '<input type="text" class="form-control" id="address" name="address" />' +
                '</div>' +
                '<div class="form-group">' +
                    '<label for="zip">ZIP</label>' +
                    '<input type="text" class="form-control" id="zip" name="zip" />' +
                '</div>' +
                '<div class="form-group">' +
                    '<label for="city">City</label>' +
                    '<input type="text" class="form-control" id="city" name="city" />' +
                '</div>' +
                '<button type="submit" class="btn btn-primary">Submit</button>' +
            '</form>'
        );

        $("#create").hide();
    });
</script>
</body>
</html>