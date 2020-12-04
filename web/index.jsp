<%--
  Created by IntelliJ IDEA.
  User: wubazx
  Date: 2020/11/20
  Time: 下午 01:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>login</title>
</head>
<script>

</script>
<style>
  *{
    background-color: beige;
    margin: 0 auto	;
  }
  .loginBox{
    top: 200px ;
    text-align: center ;
    width: 400px;
  }
  .account{
    text-align: center;

  }
  #msg{
    color: red;
  }

</style>
<body>
<div class = "loginBox">
  <h2>Login</h2>

  <div class="account">
    <form action="UserController.do?method=login" method="post">
      user：<input name = "user" type ="text"><br>
      poss：<input name = "password" type = "password"><br>
      <span id="msg">${requestScope.get("msg")}</span><br>
      <input type="submit" value="登录">
    </form>
  </div>
</div>
</body>
</html>
