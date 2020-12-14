package controller;

import bean.City;
import bean.Province;
import com.google.gson.Gson;
import dao.ProvinceCityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/queryProvinceCity")
public class queryProvinceCity extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String provinceId = request.getParameter("provinceId");
        String jsonStr = "";
        ProvinceCityDao dao = new ProvinceCityDao();
        if (provinceId ==null) {  //没有请求参数provinceID，表示查询所有省份列表
            ArrayList<Province> list = dao.queryProvince();
            jsonStr = new Gson().toJson(list);
        } else { //按provinceID查询对应的城市的列表
            ArrayList<City> list = dao.queryCity(provinceId);
            jsonStr = new Gson().toJson(list);
        }
        // 字符流输出字符串
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println(jsonStr);
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
