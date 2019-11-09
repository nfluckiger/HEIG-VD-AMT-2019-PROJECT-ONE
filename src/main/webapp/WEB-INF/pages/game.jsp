<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Games</h1>

<c:if test="${ requestScope.error != null }">
    <div class="alert alert-danger" role="alert">${ requestScope.error }</div>
</c:if>
<c:if test="${ requestScope.success != null }">
    <div class="alert alert-success" role="alert">${ requestScope.success }</div>
</c:if>

<button id="create" type="button" class="btn btn-primary" style="margin-bottom: 15px;">Add a game</button>

<ul class="list-group">
    <c:forEach items="${ requestScope.games }" var="game">
        <a href="${ pageContext.request.contextPath }/games?id=${ game.id }">
            <li class="list-group-item">${ game.away.name } @ ${ game.home.name }</li>
        </a>
    </c:forEach>
</ul>

<jsp:include page="include/endBody.jsp" />

<script type="text/javascript">
    $("#create").click(() => {
        $("#create").after(
            '<form method="post" action="${ pageContext.request.contextPath }/games?action=create">' +
                '<div class="top-margin">' +
                    '<label for="date">Date</label>' +
                    '<input type="datetime-local" class="form-control" id="date" name="date" />' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="away">Away</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="away" id="away">' +
                        <c:forEach items="${ requestScope.teams }" var="team">
                            '<option value="${ team.id }">${ team.city } ${ team.name }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="home">Home</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="home" id="home">' +
                        <c:forEach items="${ requestScope.teams }" var="team">
                            '<option value="${ team.id }">${ team.city } ${ team.name }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="referee">Referee</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="referee" id="referee">' +
                        <c:forEach items="${ requestScope.officials }" var="official">
                            '<option value="${ official.id }">${ official.lastname } ${ official.firstname }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="umpire">Umpire</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="umpire" id="umpire">' +
                        <c:forEach items="${ requestScope.officials }" var="official">
                            '<option value="${ official.id }">${ official.lastname } ${ official.firstname }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="chainJudge">Chain Judge</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="chainJudge" id="chainJudge">' +
                        <c:forEach items="${ requestScope.officials }" var="official">
                            '<option value="${ official.id }">${ official.lastname } ${ official.firstname }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="lineJudge">Line Judge</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="lineJudge" id="lineJudge">' +
                        <c:forEach items="${ requestScope.officials }" var="official">
                            '<option value="${ official.id }">${ official.lastname } ${ official.firstname }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="backJudge">Back Judge</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="backJudge" id="backJudge">' +
                        <c:forEach items="${ requestScope.officials }" var="official">
                            '<option value="${ official.id }">${ official.lastname } ${ official.firstname }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="sideJudge">Side Judge</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="sideJudge" id="sideJudge">' +
                        <c:forEach items="${ requestScope.officials }" var="official">
                            '<option value="${ official.id }">${ official.lastname } ${ official.firstname }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<div class="top-margin">' +
                    '<label for="fieldJudge">Field Judge</label>' +
                    '<select class="selectpicker registration" data-live-search="true" name="fieldJudge" id="fieldJudge">' +
                        <c:forEach items="${ requestScope.officials }" var="official">
                            '<option value="${ official.id }">${ official.lastname } ${ official.firstname }</option>' +
                        </c:forEach>
                    '</select>' +
                '</div>' +
                '<button type="submit" class="btn btn-primary" style="margin-bottom: 15px">Submit</button>' +
            '</form>'
        );

        $("#create").hide();
    });
</script>

<jsp:include page="include/footer.jsp" />