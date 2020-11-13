package filter;

import bean.User;
import dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = "/AutoLogin")
public class AutoLogin implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpSession session=request.getSession();
        User currentUser= (User) session.getAttribute("currentUser");
        if(currentUser==null){ //没有登录，检查是否记录了cookie
            //读取cookie
            Cookie[] cookies=request.getCookies();
            if(cookies!=null){//cookie存在
                String userName=null,passWord=null;
                for (Cookie cookie:cookies){//查找是否含有名为cookie_userName和cookie_passWord的cookie
                    if("cookie_userName".equals(cookie.getName())){
                        userName=cookie.getValue();
                    }
                    if("cookie_passWord".equals(cookie.getName())){
                        passWord=cookie.getValue();
                    }
                    if(userName!=null&&passWord!=null){
                        UserDao dao=new UserDao();
                        User user= null;
                            user = dao.get(userName);
                        if(user!=null){ //数据库中存在cookie_userName中存放的名为userName的用户
                            session.setAttribute("currentUser",user);
                            chain.doFilter(request,resp);
                            return;
                        }
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
