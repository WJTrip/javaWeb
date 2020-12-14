package controller;

import bean.User;
import com.google.gson.Gson;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/Register")
public class Register extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置请求参数编码格式为utf-8.防止中文参数乱码
        request.setCharacterEncoding("utf-8");
        // 1.按照表单的各元素的name属性值获取各请求参数值
        String action=request.getParameter("action");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        String chrName = request.getParameter("chrName");
        String mail = request.getParameter("mail");
        String provinceID = request.getParameter("provinceID");
        String cityID = request.getParameter("cityID");

        // 2.调用dao进行处理
        User user = new User(userName, passWord, chrName, mail, provinceID,
                cityID);
        UserDao dao = new UserDao();
        boolean success = false ;
        String info="";
        if(action.equals("") || action.equals("insert")){
            success = dao.insert(user);
            info="新增";
        }
        else if(action.equals("update")){
            success = dao.update(user);
            info="修改";
        }


        // 存放返回信息的Map
        Map<String, Object> map = new HashMap<String, Object>();
        if (success) {
            map.put("code", 0);
            map.put("info", info+"成功!");
        } else {
            map.put("code", 1);
            map.put("info", info+"失败!");
        }

        // 3.调用谷歌的Gson库将map类型数据转换为json字符串
        String jsonStr = new Gson().toJson(map);
        // 字符流输出字符串
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();

    }

}
