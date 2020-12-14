<%@ page language="java"  pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="content-type" content="text/html; charset=utf-8" />
            <title>用户管理</title>
            <link rel="stylesheet" href="css/userManage.css" />
            <link href="css/register.css" rel="stylesheet" />
            <script type="text/javascript" src="js/jquery-3.5.0.min.js"></script>
            <script type="text/javascript" src="js/userManage.js"></script>
            <script src="js/register.js?"></script>
        </head>

        <body>
            <div id="container">
                <!--引入的是静态的jsp文件,它将引入的jsp中的源代码原封不动地附加到当前文件中 -->
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
                    <div id="pageBody">
                        <div id="search">
                            <form id="searchForm">
                                <input type="text" name="userName" placeholder="输入用户名" />
                                <input type="text" name="chrName" placeholder="输入姓名" />
                                <input type="text" name="mail" placeholder="输入邮箱地址" />
                                <input type="text" name="provinceName" placeholder="输入省份" />
                            </form>
                            <div id="bt">
                                <a href="#" id="btSearch">查找</a>
                                <a href="#" id="btClear">清空</a>
                                <a href="#" id="btAdd">增加</a>
                                <a href="#" id="btDelete">删除</a>
                                <a href="#" id="btUpdate">修改</a>
                            </div>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th width="40"><input type="checkbox" id="ckAll" title="选择" /></th>
                                    <th class="bg" id="sortByUserName" data="userName">用户名</th>
                                    <th width="110">中文名</th>
                                    <th>邮箱</th>
                                    <th width="70" class="bg" id="sortByProvinceName" data="provinceName">省份</th>
                                    <th width="70">城市</th>
                                    <th width="120">操作</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                        <div class="paging">
                            <div class="pageSize"> 每页
                                <select id="pageSize">
                                <option>5</option>
                                <option selected>10</option>
                                <option >20</option></select> ， 共
                                <span id="total"></span>条数据，
                                <span id="pageNumber">1</span>页/<span id="pageCount"></span>页
                            </div>
                            <div class="pageNav">
                                <a id="first" href="#">首页</a>
                                <a id="back" href="#">上一页</a>
                                <a id="next" href="#">下一页</a>
                                <a id="last" href="#">尾页</a>
                            </div>
                        </div>
                    </div>
            </div>

            <!--弹出层时背景层DIV-->
            <div id="fade" class="black_overlay" onclick="CloseDiv('MyDiv','fade')"></div>
            <div id="MyDiv" class="white_content">
                <div style="text-align: right; height: 20px;">
                    <span style="font-size: 24px; cursor: pointer;" title="点击关闭" onclick="CloseDiv('MyDiv','fade')">×</span>
                </div>
                <div>
                    <h2 style="text-align: center;" id="formTitle">修改</h2>
                    <form id="registerForm">
                        <input id="action" name="action" type="text" hidden />
                        <p><input id="userName" name="userName" type="text" placeholder="用户名" />
                            <span class="auth_err" id="userNameError"></span>
                        </p>
                        <p><input id="chrName" name="chrName" type="text" placeholder="真实姓名" />
                            <span class="auth_err" id="chrNameError"></span>
                        </p>
                        <p><input id="mail" name="mail" type="text" placeholder="邮箱" />
                            <span class="auth_err" id="mailError"></span>
                        </p>
                        <p> <select id="provinceID" name="provinceID">
                                <option value="">请选择省份</option>
                            </select>
                            <span class="auth_err" id="provinceError"></span>
                        </p>
                        <p> <select id="cityID" name="cityID">
                                <option value="">请选择城市</option>
                            </select>
                            <span class="auth_err" id="cityError"></span>
                        </p>
                        <p><input id="passWord" name="passWord" type="password" placeholder="密码" />
                            <span class="auth_err" id="passwordError"></span>
                        </p>
                        <p><input id="passWord1" name="passWord1" type="password" placeholder="确认密码" />
                            <span class="auth_err" id="passWord1Error"></span>
                        </p>
                        <p>
                            <a href="#" id="btLogin">确定</a>
                            <span class="auth_err" id="checkError"></span>
                        </p>
                    </form>
                </div>
            </div>
        </body>

        </html>