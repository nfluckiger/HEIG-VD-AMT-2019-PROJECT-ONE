<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<h3 class="thin text-center">Sign in to your account</h3>
<hr>

<form method="post" action="./login?action=login">
  <div id="emailField" class="top-margin">
    <label>Email <span class="text-danger">*</span></label>
    <input type="text" class="form-control" name="email" />
  </div>
  <div id="passField" class="top-margin">
    <label>Password <span class="text-danger">*</span></label>
    <input type="password" class="form-control" name="password" />
  </div>

  <hr>

  <div class="row">
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
    $("#passField").after(
      '<div class="form-group">' +
        '<label for="firstname">Firstname</label>' +
        '<input type="text" class="form-control" id="firstname" name="firstname" />' +
      '</div>' +
      '<div class="form-group">' +
        '<label for="lastname">Lastname</label>' +
        '<input type="text" class="form-control" id="lastname" name="lastname" />' +
      '</div>' +
      '<div class="form-group">' +
        '<label for="email">Email</label>' +
        '<input type="email" class="form-control" id="email" name="email" />' +
      '</div>' +
      '<div class="form-group">' +
        '<label for="password">Password</label>' +
        '<input type="password" class="form-control" id="password" name="password" />' +
      '</div>'
    );

    $("form").attr("action", "${ pageContext.request.contextPath }/login?action=register");

    $("#emailField").remove();
    $("#passField").remove();
    $("#signUp").remove();
    $("#submit").text("Register");
  });
</script>

<jsp:include page="include/footer.jsp" />