<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

<h1>${ requestScope.team.name }</h1>

<table class="table">
    <tr>
        <th>Name</th>
        <td>${ requestScope.team.name }</td>
    </tr>
    <tr>
        <th>Address</th>
        <td>${ requestScope.team.address }</td>
    </tr>
    <tr>
        <th>ZIP</th>
        <td>${ requestScope.team.zip }</td>
    </tr>
    <tr>
        <th>City</th>
        <td>${ requestScope.team.city }</td>
    </tr>
</table>

<form method="post" action="${ pageContext.request.contextPath }/teams?action=delete&id=${ requestScope.team.id }">
    <button type="submit" class="btn btn-primary" style="margin-bottom: 15px">Delete this team</button>'
</form>

<jsp:include page="../include/endBody.jsp" />
<jsp:include page="../include/footer.jsp" />