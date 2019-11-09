<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

<h1>${ requestScope.official.firstname } ${ requestScope.official.lastname }</h1>

<form method="post" action="${ pageContext.request.contextPath }/officials?action=update&id=${ requestScope.official.id }">
    <div class="top-margin">
        <label for="firstname">Firstname</label>
        <input type="text" class="form-control" id="firstname" name="firstname" value="${ requestScope.official.firstname }" />
    </div>
    <div class="top-margin">
        <label for="lastname">Lastname</label>
        <input type="text" class="form-control" id="lastname" name="lastname" value="${ requestScope.official.lastname }" />
    </div>
    <div class="top-margin">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email" value="${ requestScope.official.email }" />
    </div>
    <div class="top-margin">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Leave this field blank to NOT update it"/>
    </div>
    <div class="top-margin">
        <label for="team">Team</label>
        <select class="selectpicker" data-live-search="true" name="team" id="team">
            <c:forEach items="${ requestScope.teams }" var="team">
                <option value="${ team.id }"<c:if test="${ team.id == requestScope.official.team.id}"> selected</c:if>>${ team.city } ${ team.name }</option>
            </c:forEach>
        </select>
    </div>
    <div class="top-margin">
        <label for="level">Level</label>
        <select class="selectpicker" name="level" id="level">
            <c:choose>
                <c:when test="${ requestScope.official.level == 1}">
                    <option value="1" selected>1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </c:when>
                <c:when test="${ requestScope.official.level == 2}">
                    <option value="1">1</option>
                    <option value="2" selected>2</option>
                    <option value="3">3</option>
                </c:when>
                <c:when test="${ requestScope.official.level == 3}">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3" selected>3</option>
                </c:when>
            </c:choose>
        </select>
    </div>

    <button type="submit" class="btn btn-primary" style="margin-bottom: 15px">Update</button>'
</form>

<form method="post" action="${ pageContext.request.contextPath }/officials?action=delete&id=${ requestScope.official.id }">
    <button type="submit" class="btn btn-primary" style="margin-bottom: 15px">Delete this official</button>'
</form>

<jsp:include page="../include/endBody.jsp" />
<jsp:include page="../include/footer.jsp" />