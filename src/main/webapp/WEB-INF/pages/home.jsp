<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Welcome ${ sessionScope.user.firstname } ${ sessionScope.user.lastname }!</h1>

<h3>Here is your five next games</h3>

<ul class="list-group">
    <c:forEach items="${ requestScope.games }" var="game">
        <a href="${ pageContext.request.contextPath }/games?id=${ game.id }">
            <li class="list-group-item">${ game.away.name } @ ${ game.home.name }</li>
        </a>
    </c:forEach>
</ul>

<jsp:include page="include/endBody.jsp" />
<jsp:include page="include/footer.jsp" />