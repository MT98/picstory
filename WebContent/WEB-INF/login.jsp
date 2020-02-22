<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Se connecter</title>
<link rel="stylesheet" href="./assets/login-style.css" >
<link rel="stylesheet" href="./assets/bootstrap.min.css" >
<script type="text/javascript" src="./assets/bootstrap.min.js"></script>
</head>
<body>
	<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
    <h2 class="active"> Sign In </h2>
    <h2 class="inactive underlineHover">Sign Up </h2>

   
    <!-- Login Form -->
    <form method="post">
      <input type="text" id="email" class="fadeIn second" name="email" placeholder="login">
      <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
      <input type="submit" class="fadeIn fourth" value="Se Connecter">
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="#">Forgot Password?</a>
    </div>

  </div>
</div>
</body>
</html>