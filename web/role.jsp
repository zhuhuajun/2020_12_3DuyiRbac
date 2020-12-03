<%--
  Created by IntelliJ IDEA.
  User: wubazx
  Date: 2020/11/27
  Time: 下午 03:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权396</title>
</head>
<script src="jquery-3.5.1.js"></script>
<script>
    window.onload = function () {
        var xhr = new XMLHttpRequest();
        xhr.open('get',"RoleController.do?method=list&page=1");
        function doBack(result) {
            var p = JSON.parse(result).jsonObject;
            var list = p.list;
            var tbody = document.getElementById("roleBody");
            for (var i = 0 ; i < list.length ;i ++) {
                var role = list[i];
                var tr = document.createElement('tr');
                tbody.appendChild(tr);
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);




                td1.innerHTML = role.rid;
                td2.innerHTML = role.rname;
                td3.innerHTML = '<a href = "">删除</a> <a href = "">编辑</a>';

            }

        };

        xhr.onreadystatechange =  function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                doBack(xhr.responseText);
            }
        };
        xhr.send();
    }



</script>
<body>
<h2 align="center">角色列表</h2>
    <table align="center" border="1" width="60%">
        <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody id="roleBody">

        </tbody>
    </table>
</body>
</html>
