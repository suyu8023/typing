package Servlet;

import dao.MessageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateMesinfo")
public class UpdateMesinfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int mid = Integer.parseInt(request.getParameter("mid"));
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        String message = request.getParameter("message");
        String upd_time = request.getParameter("upd_time");

        MessageDao bookdao = new MessageDao();
        try {
            bookdao.updateMesinfo(mid,name,message,level,upd_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin/messagelist.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
