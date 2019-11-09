<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Team</h1>

<c:if test="${ requestScope.error != null }">
    <div class="alert alert-danger" role="alert">${ requestScope.error }</div>
</c:if>
<c:if test="${ requestScope.success != null }">
    <div class="alert alert-success" role="alert">${ requestScope.success }</div>
</c:if>

<button id="create" type="button" class="btn btn-primary" style="margin-bottom: 15px;">Add a team</button>
<ul class="list-group">
    <c:forEach items="${ requestScope.teams }" var="team">
        <a href="${ pageContext.request.contextPath }/teams?id=${ team.id }">
            <li class="list-group-item">${ team.name }</li>
        </a>
    </c:forEach>
</ul>

<jsp:include page="include/endBody.jsp" />

<script type="text/javascript">
    $("#create").click(() => {
        $("#create").after(
            '<form method="post" action="${ pageContext.request.contextPath }/teams?action=create">' +
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
                '<button type="submit" class="btn btn-primary" style="margin-bottom: 15px">Submit</button>' +
            '</form>'
        );

        $("#create").hide();
    });
</script>

<jsp:include page="include/footer.jsp" />