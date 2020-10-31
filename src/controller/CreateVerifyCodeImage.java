package controller;

import dao.CreateVCImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(urlPatterns ="/CreateVerifyCodeImage")
public class CreateVerifyCodeImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //第一步：生成一个4位长度的字符串作为生成的验证码里面的字符串
        CreateVCImage createVCImage=new CreateVCImage();
        String vCode=createVCImage.createCode();
        //第二步：将生成的字符串保存在session范围中
        //获取session对象
        HttpSession session=request.getSession();
        //存放在session中，以便验证输入是否正确
        session.setAttribute("verifyCode",vCode);
        //设置返回内容的类型
        response.setContentType("img/jpeg");
        //浏览器不缓存响应内容（验证码图片，点一次刷新一次，所以不能有缓存出现）
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        //设置验证码的失效时间
        response.setDateHeader("Expires",0);
        //以字节流发过去，交给img的src属性去解析
        ///BufferedImage生成的图片在内存里有一个图像缓冲区
        ///利用这个缓冲区我们可以很方便的操作这个图片
        BufferedImage VCodeImage=createVCImage.CreateVCodeImage(vCode);
        ServletOutputStream out=response.getOutputStream();
        ImageIO.write(VCodeImage,"JPEG",out);
        out.flush();
        out.close();
    }
}
