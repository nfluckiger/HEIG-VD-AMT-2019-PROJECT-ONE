<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h3 class="thin text-center">Sign in to your account</h3>
<hr>

<c:if test="${requestScope.error != null}">
  <div class="alert alert-danger" role="alert">${ requestScope.error }</div>
</c:if>


<form method="post" action="./login?action=login">
  <div id="emailField" class="top-margin login">
    <label for="email">Email <span class="text-danger">*</span></label>
    <input type="text" class="form-control" id="email" name="email" />
  </div>
  <div id="passField" class="top-margin login">
    <label for="password">Password <span class="text-danger">*</span></label>
    <input type="password" class="form-control" name="password" id="password" />
  </div>

  <div class="top-margin registration" style="display: none">
    <label for="firstname">Firstname</label>
    <input type="text" class="form-control" id="firstname" name="firstname" />
  </div>
  <div class="top-margin registration" style="display: none">
    <label for="lastname">Lastname</label>
    <input type="text" class="form-control" id="lastname" name="lastname" />
  </div>
  <div class="top-margin registration" style="display: none">
    <label for="emailSignUp">Email</label>
    <input type="email" class="form-control" id="emailSignUp" name="emailSignUp" />
  </div>
  <div class="top-margin registration" style="display: none">
    <label for="passwordSignUp">Password</label>
    <input type="password" class="form-control" id="passwordSignUp" name="passwordSignUp" />
  </div>
  <div class="top-margin registration" style="display: none">
    <label for="team">Team</label>
    <select class="selectpicker registration" data-live-search="true" name="team" id="team">
      <c:forEach items="${ requestScope.teams }" var="team">
        <option value="${ team.id }">${ team.city } ${ team.name }</option>
      </c:forEach>
    </select>
  </div>
  <hr>

  <div class="row"team>
    <div class="col-lg-4">
      <button class="btn btn-action" id="signUp" type="button">Sign up</button>
    </div>
    <div class="col-lg-4">
      <button class="btn btn-action" id="submit" type="submit">Sign in</button>
    </div>
  </div>
</form>

<jsp:include page="include/endBody.jsp" />

<script type="text/javascript">
  $("#signUp").click(() => {

    $("form").attr("action", "${ pageContext.request.contextPath }/login?action=register");

    $(".login").css({ display: "none"});
    $(".registration").css({ display: "block"});

    $("#signUp").remove();
    $("#submit").text("Register");
  });
</script>

<jsp:include page="include/footer.jsp" />