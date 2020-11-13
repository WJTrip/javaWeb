package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Permission implements Filter {
    private String notCheckUri;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将ServletRequest类型参数转换为HttpServletRequest类型
        HttpServletRequest request= (HttpServletRequest) req;
        //获取请求的url-pattern地址
        String path=request.getServletPath();
        System.out.println("请求地址url-pattern:"+path);
        //请求地址不在notCheckUri范围内，需要判断是否已经登录
        if(!notCheckUri.contains(path)){
            HttpSession session=request.getSession();
            if(session.getAttribute("currentUser")==null){//没有登陆
                request.setAttribute("info","无权访问，请先登录！");
                request.getRequestDispatcher("/error.jsp").forward(request,resp);
            }else{//已经登录，直接放行
                chain.doFilter(req,resp);
            }
        }else{//请求地址再notCheckUri范围内（不需过滤），直接放行
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
        //从web.xml配置文件中读取名为notCheckUri的初始化值
        notCheckUri=config.getInitParameter("notCheckUri");
    }

}
