<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/main.css">
    <title>主界面</title>
</head>
<body>
    <div id="container">
        <div id="header">
            <div id="rightTop">
                当前用户: <span>${currentUser.chrName}</span>
                <a href="${pageContext.request.contextPath}/Logout">&nbsp;[安全退出]</a>
            </div>
            <div id="menu">
                <%--<ul> 标签定义无序列表。--%>
                <%--将 <ul> 标签与 <li> 标签一起使用，创建无序列表。--%>
                <ul>
                    <%--# 表示当前页面地址--%>
                    <li><a href="#">首页</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="${pageContext.request.contextPath}/GetDownloadList">资源下载</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="userManage.jsp">用户管理</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="#">资源管理</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="#">个人中心</a></li>
                </ul>

            </div>
            <%--<hr> 标签定义 HTML 页面中的主题变化（比如话题的转移），并显示为一条水平线。--%>
            <hr id="hr"/>
        </div>
    </div>
</body>
</html>
