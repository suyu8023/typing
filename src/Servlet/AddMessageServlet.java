package Servlet;

import dao.MessageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddMessage")
public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int uid = (int) session.getAttribute("uid");
        String name =(String) session.getAttribute("name");
        String title1 = request.getParameter("title1");
        String level1 = request.getParameter("level1");
        String message1 = request.getParameter("message1");
        String rel_time = request.getParameter("rel_time");
        String upd_time = request.getParameter("upd_time");
        MessageDao message = new MessageDao();
        try {
            message.addMessage(title1,name,message1,level1,rel_time,upd_time,uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin/messagelist.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
