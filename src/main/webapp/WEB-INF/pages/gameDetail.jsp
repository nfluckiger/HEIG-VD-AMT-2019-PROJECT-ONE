<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h1>${ requestScope.game.away.name } vs ${ requestScope.game.home.name }</h1>

<table class="table">
    <tr>
        <th>Away</th>
        <td>
            <a href="${ pageContext.request.contextPath }/teams?id=${ requestScope.game.away.id }">${ requestScope.game.away.name }</a>
        </td>
    </tr>
    <tr>
        <th>Home</th>
        <td>
            <a href="${ pageContext.request.contextPath }/teams?id=${ requestScope.game.home.id }">${ requestScope.game.home.name }</a>
        </td>
    </tr>
    <tr>
        <th>Address</th>
        <td>${ requestScope.game.home.address }</td>
    </tr>
    <tr>
        <th>ZIP</th>
        <td>${ requestScope.game.home.zip }</td>
    </tr>
    <tr>
        <th>City</th>
        <td>${ requestScope.game.home.city }</td>
    </tr>
    <tr>
        <th>Referee</th>
        <td>
            <a hre="${pageContext.request.contextPath }/officials?id=${ requestScope.game.referee.id }">
                ${ requestScope.game.referee.firstname } ${ requestScope.game.referee.lastname }
            </a>
        </td>
    </tr>
    <tr>
        <th>Umpire</th>
        <td>
            <a hre="${pageContext.request.contextPath }/officials?id=${ requestScope.game.umpire.id }">
                ${ requestScope.game.umpire.firstname } ${ requestScope.game.umpire.lastname }
            </a>
        </td>
    </tr>
    <tr>
        <th>Chain Judge</th>
        <td>
            <a hre="${pageContext.request.contextPath }/officials?id=${ requestScope.game.chainJudge.id }">
                ${ requestScope.game.chainJudge.firstname } ${ requestScope.game.chainJudge.lastname }
            </a>
        </td>
    </tr>
    <tr>
        <th>Line Judge</th>
        <td>
            <a hre="${pageContext.request.contextPath }/officials?id=${ requestScope.game.lineJudge.id }">
                ${ requestScope.game.lineJudge.firstname } ${ requestScope.game.lineJudge.lastname }
            </a>
        </td>
    </tr>
    <tr>
        <th>Back Judge</th>
        <td>
            <a hre="${pageContext.request.contextPath }/officials?id=${ requestScope.game.backJudge.id }">
                ${ requestScope.game.backJudge.firstname } ${ requestScope.game.backJudge.lastname }
            </a>
        </td>
    </tr>
    <tr>
        <th>Side Judge</th>
        <td>
            <a hre="${pageContext.request.contextPath }/officials?id=${ requestScope.game.sideJudge.id }">
                ${ requestScope.game.sideJudge.firstname } ${ requestScope.game.sideJudge.lastname }
            </a>
        </td>
    </tr>
    <tr>
        <th>Field Judge</th>
        <td>
            <a hre="${pageContext.request.contextPath }/officials?id=${ requestScope.game.fieldJudge.id }">
                ${ requestScope.game.fieldJudge.firstname } ${ requestScope.game.fieldJudge.lastname }
            </a>
        </td>
    </tr>
</table>

<form method="post" action="${ pageContext.request.contextPath }/games?action=delete&id=${ requestScope.game.id }">
    <button type="submit" class="btn btn-primary" style="margin-bottom: 15px">Delete this Game</button>'
</form>

<jsp:include page="include/endBody.jsp" />
<jsp:include page="include/footer.jsp" />