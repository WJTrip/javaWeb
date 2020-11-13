package controller;

import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/Logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User currentUser= (User) session.getAttribute("currentUser");
        if(currentUser!=null){
            //删除cookie
            Cookie cookie_userName=new Cookie("userName","");
            cookie_userName.setMaxAge(0);
            response.addCookie(cookie_userName);
            Cookie cookie_passWord=new Cookie("passWord","");
            cookie_passWord.setMaxAge(0);
            response.addCookie(cookie_passWord);
        }
        String forwardPath="/login.html";
        RequestDispatcher rd=request.getRequestDispatcher(forwardPath);
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
