<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

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

<jsp:include page="include/footer.jsp" />