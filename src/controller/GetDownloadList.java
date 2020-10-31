package controller;

import bean.Download;
import dao.DownloadDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/GetDownloadList")
public class GetDownloadList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DownloadDao dao=new DownloadDao();
        ArrayList<Download> downloadList=new ArrayList<>();
        try {
            downloadList=dao.getAllDownloadList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("downloadList",downloadList);

        RequestDispatcher rd=request.getRequestDispatcher("/download.jsp");
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
