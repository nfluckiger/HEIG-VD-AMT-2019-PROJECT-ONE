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
<h1>Officials</h1>
<button id="create" type="button" class="btn btn-primary">Add a team</button>
<ul class="list-group">
    <c:forEach items="${ requestScope.officials }" var="official">
        <li class="list-group-item"><c:out value="${ official.firstname } ${ official.lastname }" /></li>
    </c:forEach>
</ul>
<script type="text/javascript">
    $("#create").click(() => {
        $("#create").after(
            '<form method="post" action="${ pageContext.request.contextPath }/officials">' +
                '<div class="form-group">' +
                    '<label for="firstname">Firstname</label>' +
                    '<input type="text" class="form-control" id="firstname" name="firstname" />' +
                '</div>' +
                '<div class="form-group">' +
                    '<label for="lastname">Lastname</label>' +
                    '<input type="text" class="form-control" id="lastname" name="lastname" />' +
                '</div>' +
                '<div class="form-group">' +
                    '<label for="email">Email</label>' +
                    '<input type="email" class="form-control" id="email" name="email" />' +
                '</div>' +
                '<div class="form-group">' +
                    '<label for="password">Password</label>' +
                    '<input type="password" class="form-control" id="password" name="password" />' +
                '</div>' +
                '<button type="submit" class="btn btn-primary">Submit</button>' +
            '</form>'
        );

        $("#create").hide();
    });
</script>
</body>
</html>