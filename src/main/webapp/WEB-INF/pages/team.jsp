<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Team</h1>

<c:if test="${id != null}">
    <c:choose>
        <c:when test="${id == -1}">
            <div class="alert alert-danger" role="alert">Unable to create team</div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-success" role="alert">Team created</div>
        </c:otherwise>
    </c:choose>
</c:if>

<button id="create" type="button" class="btn btn-primary" style="margin-bottom: 15px;">Add a team</button>
<ul class="list-group">
    <c:forEach items="${ requestScope.teams }" var="team">
        <li class="list-group-item"><c:out value="${ team.name }" /></li>
    </c:forEach>
</ul>

<jsp:include page="include/endBody.jsp" />

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
                '<button type="submit" class="btn btn-primary" style="margin-bottom: 15px">Submit</button>' +
            '</form>'
        );

        $("#create").hide();
    });
</script>

<jsp:include page="include/footer.jsp" />