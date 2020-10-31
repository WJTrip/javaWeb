package controller;

import bean.User;
import dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获取输入框的内容
        String userName=request.getParameter("userName");
        String passWord=request.getParameter("passWord");
        String userCode=request.getParameter("userCode");

        //跳转地址
        String forwardPath="";

        HttpSession session=request.getSession();
        String createdCode= (String) session.getAttribute("verifyCode");

        //用户登录信息判断
        //equalsIgnoreCase() 方法用于将字符串与指定的对象比较，不考虑大小写。
        if(!userCode.equalsIgnoreCase(createdCode)){//验证码错误
            request.setAttribute("info","验证码错误!");
            forwardPath="/error.jsp";
        }else{//验证码正确
            UserDao dao=new UserDao();
            User user=null;
            try {
                user=dao.get(userName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (user == null) { //用户名不存在
                request.setAttribute("info","用户名不存在!");
                forwardPath="/error.jsp";
            }else{//用户名存在
                if(!user.getPassWord().equals(passWord)) {//密码错误
                    request.setAttribute("info", "密码错误!");
                    forwardPath = "/error.jsp";
                }else{//密码正确
                    session.setAttribute("currentUserName",user.getChrName());
                    forwardPath="/main.jsp";
                }
                ///请求调度器 接口 提供调度request到另一个资源（servlet/jsp/html）的功能。
                /////转发（调度）请求给/forwardPath.jsp 并由forwardPath.jsp发送response给客户端。

            }
        }
        RequestDispatcher rd=request.getRequestDispatcher(forwardPath);
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
