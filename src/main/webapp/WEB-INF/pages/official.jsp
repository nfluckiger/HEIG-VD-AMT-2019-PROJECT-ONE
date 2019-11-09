<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Officials</h1>
<ul class="list-group">
    <c:forEach items="${ requestScope.officials }" var="official">
        <a href="${ pageContext.request.contextPath }/officials?id=${ official.id }">
            <li class="list-group-item">${ official.firstname } ${ official.lastname }</li>
        </a>
    </c:forEach>
</ul>

<jsp:include page="include/endBody.jsp" />
<jsp:include page="include/footer.jsp" />