<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <script>
            function toDelete (uid) {

                // 弹出一个选择框
                var f = confirm('是否确定删除');
                if(f) {
                    location.href = 'UserController.do?method=delete&uid='+uid;
                }else{
                    toDelete(uid);
                }
            }
        </script>
    </head>
    <body>
        <h2 align="center">用户列表</h2>
        <h3 align="center">
            <a href="userAdd.jsp">新建用户</a>
            <a href="userImport.jsp">导入用户</a>
        </h3>
        <table align="center" width="90%" border="1">
            <thead>
                <tr>
                    <th>用户编号</th>
                    <th>用户名</th>
                    <th>真实姓名</th>
                    <th>用户年龄</th>
                    <th>用户性别</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.page.list}" var="user">
                    <tr align="center">
                        <td>${user.uid}</td>
                        <td>${user.UName}</td>
                        <td>${user.URueName}</td>
                        <td>${user.sex}</td>
                        <td>${user.age}</td>
                        <td>
                            <a href="UserController.do?method=edit&uid=${user.uid}">编辑</a> |
                            <!-- int uid = 10 ; toDelete(uid) -->
                            <a href="javascript:toDelete(${user.uid})">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <table width="90%" align="center">
            <tr>
                <td align="left">
                    第${requestScope.page.page}页/共${requestScope.page.max}页
                </td>
                <td align="right">
                    <a href="UserController.do?method=list&page=1">首页</a>
                    <c:if test="${requestScope.page.page > 1}">
                        <a href="UserController.do?method=list&page=${requestScope.page.page-1}">上一页</a>
                    </c:if>
                    <c:if test="${requestScope.page.page < requestScope.page.max}">
                        <a href="UserController.do?method=list&page=${requestScope.page.page+1}">下一页</a>
                    </c:if>
                    <a href="UserController.do?method=list&page=${requestScope.page.max}">未页</a>
                </td>
            </tr>
        </table>
    </body>
</html>
