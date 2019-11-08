<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>Officials</h1>
<button id="create" type="button" class="btn btn-primary">Add an official</button>
<ul class="list-group">
    <c:forEach items="${ requestScope.officials }" var="official">
        <li class="list-group-item"><c:out value="${ official.firstname } ${ official.lastname }" /></li>
    </c:forEach>
</ul>

<jsp:include page="include/footer.jsp" />