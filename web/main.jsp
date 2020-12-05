<%@ page pageEncoding="utf-8" %>
<html>
<head>
    <title>主页</title>
    <style>
        .top{
            width:100%;
            height:118px;
            position:absolute ;
            top:0;
            left:0;
            border-bottom:2px solid #ccc;
        }
        .left{
            width:248px;
            position:absolute ;
            top:120px;
            left:0;
            bottom:0;
            border-right:2px solid #ccc;
        }
        .right{
            position:absolute ;
            top:120px;
            left:250px;
            bottom:0;
            right:0;

        }
        .title{
            font-size:40px;
            margin-left:20px;
        }

        .msg{
            position: absolute;
            right:100px;
            top:80px;
        }
        .menuBox{
            margin:20px;
        }
        .menuBox dt{
            margin-top:4px;
            margin-bottom:4px;
        }
    </style>
</head>
<script>
    window.onload =  function () {

    }


</script>

<body>
<div class="top">
    <h3 class="title"></h3>
    <div class="msg">welcome to <a href="UserController.do?method=exit">${sessionScope.user.UName}</a></div>
</div>
<div class="left">

    <dl class="menuBox">
        <dt>1</dt>
        <dd>
            <dl>
                <dt><a href="UserController.do?method=list&page=1" target="content">用hgl</a></dt>
                <dt><a href="role.jsp" target="content">权396</a></dt>
                <dt><a href="FnController.do?method=list" target="content">fn</a></dt>
            </dl>
        </dd>

    </dl>

</div>
<div class="right">
    <!-- 内嵌的子窗口 -->
    <iframe name="content" frameborder="0" width="100%" height="100%"></iframe>
</div>
</body>
</html>
