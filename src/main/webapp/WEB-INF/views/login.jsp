<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Login Form</title>
</head>
<body>
<form action="login" method="post">
  <table style="with: 50%">

    <tr>
      <td>Email</td>
      <td><input type="text" name="email"/></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" name="password"/></td>
    </tr>
  </table>
  <input type="submit" value="Login"/></form>
</body>
</html>