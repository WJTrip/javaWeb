<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/28
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>出错了！</title>
    <link rel="stylesheet" href="css/error.css">
    <script src="js/error.js"></script>
</head>
<body>
    <div id="errorDiv">
        <div id="errorHint">
            <p id="errorInfo">${info}</p>
            <p><span id="leaveTime">10</span>秒后自动返回到登录页面</p>
            <p>点击<a href="${pageContext.request.contextPath}/login.html">这里</a>回到登录页面</p>
        </div>
    </div>
</body>
</html>
