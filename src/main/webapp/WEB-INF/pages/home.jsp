<jsp:include page="include/header.jsp" />

<h1>Welcome ${ sessionScope.user.firstname } ${ sessionScope.user.lastname }!</h1>
<p>I am running from a WAR artifact.</p>

<jsp:include page="include/endBody.jsp" />
<jsp:include page="include/footer.jsp" />