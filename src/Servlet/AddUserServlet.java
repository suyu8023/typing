package Servlet;

import dao.MessageDao;
import dao.UserDao;
import util.NetworkUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        HttpSession session = request.getSession();
//        int uid = (int) session.getAttribute("uid");
        String username =request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        NetworkUtil ipadd = new NetworkUtil();
        String ip = ipadd.getIPAddress(request);
        String login_time = request.getParameter("upd_time");
        String reg_time = request.getParameter("rel_time");
        UserDao user = new UserDao();
        try {
            user.addUser(username,nickname,email,password,ip,login_time,reg_time,2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin/user.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
