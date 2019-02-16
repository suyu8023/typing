package Servlet;

import dao.MessageDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取图书id
        int uid = Integer.parseInt(request.getParameter("uid"));
        UserDao user = new UserDao();
        //删除图书
        try {
            user.deleteUser(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("user.html");
    }
}
