<jsp:include page="include/header.jsp" />

<div class="navbar navbar-inverse navbar-fixed-top headroom" >
  <div class="container">
    <div class="navbar-header">
      <!-- Button for smallest screens -->
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" href="index.html"><img src="assets/images/logo.png" alt="Progressus HTML5 template"></a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav pull-right">
        <li><a href="${ pageContext.request.contextPath }/games">GAAAAAAAAA</a></li>
        <li><a href="${ pageContext.request.contextPath }/teams">Teams</a></li>
        <li><a href="${ pageContext.request.contextPath }/officials">Officials</a></li>
      </ul>
    </div>
  </div>
</div>

<header id="head" class="secondary"></header>

<div class="container" style="margin-top:20px">
  <div class="row">
    <article class="col-xs-12 maincontent">
      <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3 class="thin text-center">Sign in to your account</h3>
            <p class="text-center text-muted">If you don't have an account, you can sign up <a href="signup.html">here</a></p>
            <hr>

            <form>
              <div class="top-margin">
                <label>Email <span class="text-danger">*</span></label>
                <input type="text" class="form-control">
              </div>
              <div class="top-margin">
                <label>Password <span class="text-danger">*</span></label>
                <input type="password" class="form-control">
              </div>

              <hr>

              <div class="row">
                <div class="col-lg-4">
                  <button class="btn btn-action" type="submit">Sign in</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </article>
  </div>
</div>


<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="../../assets/js/headroom.min.js"></script>
<script src="../../assets/js/jQuery.headroom.min.js"></script>
<script src="../../assets/js/template.js"></script>

<jsp:include page="include/footer.jsp" />