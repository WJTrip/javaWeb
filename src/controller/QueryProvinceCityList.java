package controller;

import bean.City;
import bean.Province;
import com.google.gson.Gson;
import dao.CityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/ajaxQueryProvinceCity")
public class QueryProvinceCityList extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String provinceID=request.getParameter("provinceID");
		CityDao dao=new CityDao();
		String jsonStr="";
		if(provinceID==null){
			ArrayList<Province> list=dao.queryProvince();
			jsonStr=new Gson().toJson(list);
			
		}
		else
		{
			ArrayList<City> list=dao.queryCity(provinceID);
			jsonStr=new Gson().toJson(list);
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
