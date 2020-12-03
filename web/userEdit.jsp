<%--
  Created by IntelliJ IDEA.
  User: wubazx
  Date: 2020/11/25
  Time: 下午 03:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新建</title>
</head>
<style>
    *{
        margin: 0 auto;
    }
    .userAddBox{
        width: 400px;
        height: 250px;
        text-align: center;
        background-color: aquamarine;
    }


</style>
<script src="jquery-3.5.1.js" ></script>
<script>
    $(document).ready(function () {
        $(".rPassword").blur(function () {
            var pass = $(".password").val();

            var rpass = $(".rPassword").val();

            if (pass != rpass) {
                $("#error").val("两次输入密码不一致");
                $(".rPassword").val("");
            }
        });
    });
</script>
<body>
<div class="userEditBox">
    <h2 align="center">编辑</h2>
    <form action="UserController.do?method=update&uid=${requestScope.user.uid}" method="post">
        <table align="center">
            <tr>
                <td>用户名：</td>
                <td><input name="uName" type="text" required="required" value="${requestScope.user.UName}"/></td>
            </tr>

            <tr>
                <td>真实姓名：</td>
                <td><input name="uRueName" type="text"  required="required" value="${requestScope.user.URueName}" /></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input class="password" name="uPass" id="upass" type="password"  required="required" value="${requestScope.user.UPass}" /></td>
            </tr>

            <tr>
                <td>确认密码：</td>
                <td><input class="rPassword" type="password" id="repass"  required="required" value="${requestScope.user.UPass}"  /></td><span id="error"></span>
            </tr>

            <tr>
                <td>年龄：</td>
                <td><input name="age" type="number"  required="required" value="${requestScope.user.age}" /></td>
            </tr>

            <tr>
                <td>性别：</td>
                <td>
                    <select name="sex">
                        <option>男</option>
                        <option>女</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <button>保存</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
