<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Games</h1>
<ul class="list-group">
    <c:forEach items="${ requestScope.games }" var="game">
        <li class="list-group-item"><c:out value="${ game.away.name } @ ${ game.home.name }" /></li>
    </c:forEach>
</ul>

<jsp:include page="include/endBody.jsp" />
<jsp:include page="include/footer.jsp" />