<%--
  Created by IntelliJ IDEA.
  User: zhj
  Date: 2020/12/7
  Time: 上午 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>功能管理</title>
</head>
<style>
    ul li {
        float: left;
        list-style: none;
        margin-right: 30px;
    }
    #menuBox {
        width: 300px;
    }



</style>
<body>
<script>
    window.onload = function () {
        var xhr = new XMLHttpRequest();
        xhr.open('get',"FnController.do?method=list");

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                doBack(xhr.responseText);

            }
        }

        function doBack (result){

            var list = JSON.parse(result).jsonObject;

            var position = document.getElementById('menuBox');
            showFn(list,position)
        }
        xhr.send();
        function showFn (result,position) {
            for (var i = 0 ; i < result.length; i++) {
                var fn = result[i];
                var dt = document.createElement('dt');

                var ul = document.createElement('ul');
                var l1 = document.createElement('li');
                var l2 = document.createElement('li');
                var l3 = document.createElement('li');
                position.appendChild(dt);
                dt.appendChild(ul);
                ul.appendChild(l1);
                ul.appendChild(l2);
                ul.appendChild(l3);

                l1.innerHTML = fn.fname;
                l2.innerHTML = fn.fhref == ""?"无": fn.fhref;
                l3.innerHTML = fn.flag == 1?"菜单":'按钮';

                if (fn.carte != "") {
                    var dd = document.createElement('dd');
                    dt.appendChild(dd)
                    showFn(fn.carte,dd)
                }

            }

        }

    }


</script>

<div>

    <dl id="menuBox"></dl>
</div>
</body>
</html>
