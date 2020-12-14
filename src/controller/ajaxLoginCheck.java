package controller;

import bean.User;
import com.google.gson.Gson;
import dao.UserDao;

import javax.lang.model.element.NestingKind;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/ajaxLoginCheck")
public class ajaxLoginCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(1111);
        request.setCharacterEncoding("utf-8");
        String userName=request.getParameter("userName");
        String passWord=request.getParameter("passWord");
        String userCode=request.getParameter("userCode");
        String checkBox=request.getParameter("checkBox");

        HttpSession session=request.getSession();
        String createdCode= (String) session.getAttribute("verifyCode");

        //存放返回信息的map
        Map<String,Object> map=new HashMap<String, Object>();

        if(!userCode.equalsIgnoreCase(createdCode)){//验证码错误
            //在map中存放返回的数据
            map.put("code",1);
            map.put("info","验证码不正确！");
        }else{//验证码正确
            UserDao userDao=new UserDao();
            User user=userDao.get(userName);
            if(user==null){
                map.put("code",2);
                map.put("info","用户名不存在！");
            }else{//用户名存在
                if(!user.getPassWord().equals(passWord)){//密码不正确
                    map.put("code",3);
                    map.put("info","密码不正确！");
                }else{//用户名密码验证码都正确
                    session.setAttribute("currentUser",user);
                    if(checkBox!=null){//勾选了一周内免登录
                        Cookie userName_Cookie=new Cookie("userName",userName);
                        Cookie passWord_Cookie=new Cookie("passWord",passWord);
                        userName_Cookie.setMaxAge(7*24*60*60);
                        passWord_Cookie.setMaxAge(7*24*60*60);
                        response.addCookie(userName_Cookie);
                        response.addCookie(passWord_Cookie);
                    }
                    map.put("code",0);
                    map.put("info","登录成功");
                }
            }
        }
        String jsonString=new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out=response.getWriter();
//        out.print(jsonString);
//        out.flush();
//        out.close();
        response.getWriter().write(jsonString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
