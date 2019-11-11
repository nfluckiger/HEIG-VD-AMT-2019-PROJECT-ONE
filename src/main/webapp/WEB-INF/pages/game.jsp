<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Games</h1>

<c:if test="${ requestScope.error != null }">
    <div class="alert alert-danger" role="alert">${ requestScope.error }</div>
</c:if>
<c:if test="${ requestScope.success != null }">
    <div class="alert alert-success" role="alert">${ requestScope.success }</div>
</c:if>

<c:if test="${ sessionScope.user.level == 3}">
    <button id="create" type="button" class="btn btn-primary" style="margin-bottom: 15px;">Add a game</button>
</c:if>

<ul class="list-group">
    <c:forEach items="${ requestScope.games }" var="game">
        <a href="${ pageContext.request.contextPath }/games?id=${ game.id }">
            <li class="list-group-item">${ game.away.name } @ ${ game.home.name }</li>
        </a>
    </c:forEach>
</ul>

<!-- Pagination -->
<c:choose>
    <c:when test="${ empty param['page']}">
        <c:set var="currentPage" value="1" scope="page" />
    </c:when>
    <c:otherwise>
        <c:set var="currentPage" value="${ param['page'] }" scope="page" />
    </c:otherwise>
</c:choose>
<nav aria-label="Page navigation" style="text-align: center;">
    <ul class="pagination">
        <c:choose>
            <c:when test="${ requestScope.nbTabs > 6 }">
                <c:choose>
                    <%-- in the 3 first pages --%>
                    <c:when test="${ currentPage <= 3 }">
                        <li class="page-item <c:if test="${ currentPage == 1}">disabled</c:if>">
                            <a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ currentPage - 1 }">Prev</a>
                        </li>
                        <c:forEach var="i" begin="1" end="${ 5 }">
                            <li class="page-item">
                                <a class="page-link <c:if test="${i == currentPage}">selected</c:if>" href="${ pageContext.request.contextPath }/games?page=${ i }">
                                    ${ i }
                                </a>
                            </li>
                        </c:forEach>
                        <li class="page-item"><a class="page-link">...</a></li>
                        <li class="page-item"><a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ requestScope.nbTabs }">${ requestScope.nbTabs }</a></li>
                        <li class="page-item">
                            <a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ currentPage + 1 }">Next</a>
                        </li>
                    </c:when>

                    <%-- in the 3 last pages --%>
                    <c:when test="${ currentPage > requestScope.nbTabs - 3 }">
                        <li class="page-item">
                            <a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ currentPage - 1}">Prev</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="${ pageContext.request.contextPath }/games?page=1">1</a></li>
                        <li class="page-item"><a class="page-link">...</a></li>
                        <c:forEach var="i" begin="${ requestScope.nbTabs - 4 }" end="${ requestScope.nbTabs }">
                            <li class="page-item">
                                <a class="page-link <c:if test="${i == currentPage}">selected</c:if>" href="${ pageContext.request.contextPath }/games?page=${ i }">
                                    ${ i }
                                </a>
                            </li>
                        </c:forEach>
                        <li class="page-item <c:if test="${ currentPage == requestScope.nbTabs }">disabled</c:if>">
                            <a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ requestScope.nbTabs }">Next</a>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ currentPage - 1}">Prev</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="${ pageContext.request.contextPath }/games">1</a></li>
                        <c:if test="${ currentPage != 4 }">
                            <li class="page-item"><a class="page-link">...</a></li>
                        </c:if>

                        <c:forEach var="i" begin="${ currentPage - 2 }" end="${ currentPage + 2 }">
                            <li class="page-item">
                                <a class="page-link <c:if test="${i == currentPage}">selected</c:if>" href="${ pageContext.request.contextPath }/games?page=${ i }">
                                    ${ i }
                                </a>
                            </li>
                        </c:forEach>

                        <c:if test="${ currentPage != requestScope.nbTabs - 3}">
                            <li class="page-item"><a class="page-link">...</a></li>
                        </c:if>
                        <li class="page-item"><a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ requestScope.nbTabs }">${ requestScope.nbTabs }</a></li>
                        <li class="page-item">
                            <a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ currentPage + 1 }">Next</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:when>

            <c:otherwise>
                <li class="page-item <c:if test="${ currentPage == 1 }">disabled</c:if>">
                    <a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ currentPage - 1}">Prev</a>
                </li>

                <c:forEach var="i" begin="1" end="${ requestScope.nbTabs }">
                    <li class="page-item">
                        <a class="page-link <c:if test="${i == currentPage}">selected</c:if>" href="${ pageContext.request.contextPath }/games?page=${ i }">
                            ${ i }
                        </a>
                    </li>
                </c:forEach>

                <li class="page-item <c:if test="${ currentPage == requestScope.nbTabs }">disabled</c:if>">
                    <a class="page-link" href="${ pageContext.request.contextPath }/games?page=${ currentPage + 1 }">Next</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>

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