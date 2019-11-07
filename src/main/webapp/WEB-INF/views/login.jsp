<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello Worlasdsadsdasdsadasdsd!</h2>--%>
<%----%>
<%--<a href="${pageContext.request.contextPath}/login">test</a>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport"    content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">

  <title>Sign in | Official League</title>
  <base href="${ pageContext.request.contextPath }/" />

  <link rel="shortcut icon" href="../../res/assets/images/gt_favicon.png">

  <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
  <link rel="stylesheet" href="../../res/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="../../res/assets/css/font-awesome.min.css">

  <!-- Custom styles for our template -->
  <link rel="stylesheet" href="../../res/assets/css/bootstrap-theme.css" media="screen" >
  <link rel="stylesheet" href="../../res/assets/css/main.css">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="../../res/assets/js/html5shiv.js"></script>
  <script src="assets/js/respond.min.js"></script>
  <![endif]-->
</head>

<body>
<!-- Fixed navbar -->
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
<script src="../../res/assets/js/headroom.min.js"></script>
<script src="../../res/assets/js/jQuery.headroom.min.js"></script>
<script src="../../res/assets/js/template.js"></script>
</body>
</html>