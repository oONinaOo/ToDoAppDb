<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>TodoApp</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<div id="logintext" class="logintext">Login</div>
<div id="login" class="login">
<form action="login" method="post">
        <span class="username">Username: </span><input type="text" size = "10" placeholder="Username" name="logusername">
       <span class="password">Password: </span><input type="password" placeholder="Password" name="logpw" size = "10">
        <input type="submit" value="Login" class="myButton loginbutton">
</form>
</div>
<div id="registertext" class="registertext">Register</div>
<div id="register" class="register">
    <form action="register" method="post">
        <span class="username">Username: </span><input type="text" size = "10" placeholder="Username" name="regusername">
        <span class="password">Password: </span><input type="password" placeholder="Password" name="regpw" size = "10">
        <input type="submit" value="Register" class="myButton registerbutton">
    </form>
</div>
<script type="text/javascript" src="script.js"></script>
</body>
</html>

