<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-inverse navbar-fixed-top headroom" >
    <div class="container">
        <div class="navbar-header">
            <!-- Button for smallest screens -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
            <a class="navbar-brand" href="${ pageContext.request.contextPath }/home"><img src="./assets/images/logo.png" alt="Progressus HTML5 template"></a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav pull-right">
                <li><a href="${ pageContext.request.contextPath }/home">Home</a></li>
                <li><a href="${ pageContext.request.contextPath }/games">Games</a></li>
                <li><a href="${ pageContext.request.contextPath }/teams">Teams</a></li>
                <li><a href="${ pageContext.request.contextPath }/officials">Officials</a></li>
                <c:if test="${ sessionScope.user != null}">
                    <li><a href="${ pageContext.request.contextPath }/login?action=logout">Log out</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<header id="head" class="secondary"></header>

<jsp:include page="body.jsp" />