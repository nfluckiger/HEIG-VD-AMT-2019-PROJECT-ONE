<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Officials</h1>

<c:if test="${ requestScope.error != null }">
    <div class="alert alert-danger" role="alert">${ requestScope.error }</div>
</c:if>
<c:if test="${ requestScope.success != null }">
    <div class="alert alert-success" role="alert">${ requestScope.success }</div>
</c:if>

<ul class="list-group">
    <c:forEach items="${ requestScope.officials }" var="official">
        <a href="${ pageContext.request.contextPath }/officials?id=${ official.id }">
            <li class="list-group-item">${ official.lastname } ${ official.firstname }</li>
        </a>
    </c:forEach>
</ul>

<jsp:include page="include/endBody.jsp" />
<jsp:include page="include/footer.jsp" />