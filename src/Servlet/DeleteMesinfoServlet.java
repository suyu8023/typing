package Servlet;

import dao.MessageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/DeleteMesinfo")
public class DeleteMesinfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取图书id
        int mid = Integer.parseInt(request.getParameter("mid"));
        MessageDao message = new MessageDao();
        //删除图书
        try {
            message.deleteMes(mid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("messagelist.html");
    }
}
