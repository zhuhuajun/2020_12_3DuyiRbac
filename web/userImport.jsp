
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>批量导入</title>
</head>
<style>
    table {
        width: 300px;
        height: 300px;
        background-color: darkcyan;
        align-content: center;
        margin: 0 auto;
    }
    .title {
        text-align: center;
    }
</style>
<body>
<form action="UserController.do?method=batch" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td class="title">选择文件</td>
        </tr>
        <tr>
            <td><input type="file" name="excel" ></td>
        </tr>
        <tr><td><a href="">模板下载</a></td></tr>

        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
