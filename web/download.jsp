<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Download" %>
<%@ page import="dao.DownloadDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/29
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" >
    <title>资源下载</title>
    <link href="${pageContext.request.contextPath}/css/download.css?t=2" rel="stylesheet" type="text/css">
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="container">
        <div id="header">
            <div id="rightTop">
                当前用户: <span>&nbsp;${currentUser.chrName}</span>
                <a href="${pageContext.request.contextPath}/Logout">&nbsp;&nbsp;[安全退出]</a>
            </div>
            <div id="menu">
                <%--<ul> 标签定义无序列表。--%>
                <%--将 <ul> 标签与 <li> 标签一起使用，创建无序列表。--%>
                <ul>
                    <%--# 表示当前页面地址--%>
                    <li><a href="main.jsp">首页</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="GetDownloadList">资源下载</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="#">用户管理</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="userManage.jsp">资源管理</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="#">个人中心</a></li>
                </ul>

            </div>
            <%--<hr> 标签定义 HTML 页面中的主题变化（比如话题的转移），并显示为一条水平线。--%>
            <hr id="hr"/>
        </div>

        <%
            ArrayList<Download> list= (ArrayList<Download>) request.getAttribute("downloadList");
            DownloadDao dao=new DownloadDao();
            /*for (int i = list.size() - 1; i >= 0; i--) {
                Download download=list.get(i);
                String id=download.getId();
                try {
                    String html = "<ul>"
                            +"<li>"
                            +"<p class=\"name\">"+dao.get(id).getName()+"</p>"
                            +"<div class=\"pic-txt\">"
                            +"<img class=\"img-area\" src=\""+dao.get(id).getPath()+"\"  alt=\"\"/>"
                            +"<p class=\"txt-area\">"
                            +"<span class=\"\">"+dao.get(id).getDescription()+"</span>"
                            +"<span class=\"tit-sub\">"
                            +"<i class=\"space\">大小："+dao.get(id).getSize()+"b</i>"
                            +"<i>星级:"+dao.get(id).getStart()+"</i>"
                            +"</span>"
                            +"</p>"
                            +"</div>"
                            +"<a class=\"dl-btn\" href=\"${pageContext.request.contextPath}/Download?id="+id+"\" title=\"点击下载\">下载</a>"
                            +"</li>"
                            +"</ul>";
                    PrintWriter pw=new PrintWriter("/download.jsp");
                    pw.print(html);
                    pw.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

             */
        %>
        <div class="pageBody">
            <%
                for (Download download : list) {
            %>
            <ul>
                <li>
                    <p class="name">
                        <%=dao.get(download.getId()).getName()%>
                    </p>
                    <div class="pic-txt">
                        <img src="<%=dao.get(download.getId()).getImage()%>" alt="" class="img-area">
                        <p class="txt-area">
                        <span class="" >
                            <%=dao.get(download.getId()).getDescription()%>
                        </span>
                        <span class="tit-sub">
                            <i class="space">大小：<%=dao.get(download.getId()).getSize()%>b</i>
                            <i>星数: <%=dao.get(download.getId()).getStart()%></i>
                        </span>
                        </p>
                    </div>
                    <a class="dl-btn" href="${pageContext.request.contextPath}/Download?<%=download.getId()%>" title="点击下载">下载</a>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
