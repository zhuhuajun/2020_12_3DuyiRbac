
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
            var pass = $(".uPass").val();

            var rpass = $(".rPassword").val();

            if (pass != rpass) {
                alert("两次密码不一致");
                $(".rPassword").val("");
            }
        });
    });
</script>
<body>
<div class="userAddBox">
    <h2 align="center">新建用户</h2>
    <form action="UserController.do?method=save" method="post">
        <table align="center">
            <tr>
                <td>用户名：</td>
                <td><input name="uName" type="text" required="required" /></td>
            </tr>

            <tr>
                <td>密码：</td>
                <td><input class="uPass" name="upass" id="upass" type="password"  required="required" /></td>
            </tr>

            <tr>
                <td>确认密码：</td>
                <td><input class="rPassword" type="password" id="repass"  required="required"  /></td>
            </tr>

            <tr>
                <td>真实姓名：</td>
                <td><input name="uRueName" type="text"  required="required"  /></td>
            </tr>

            <tr>
                <td>年龄：</td>
                <td><input name="age" type="number"  required="required"  /></td>
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
