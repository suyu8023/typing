package Servlet;

import dao.UserDao;
import util.NetworkUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateUserinfo")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int uid = Integer.parseInt(request.getParameter("uid"));
        String username =request.getParameter("username");
        String ss =request.getParameter("ss");
        String ss1 =request.getParameter("ss1");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        NetworkUtil ipadd = new NetworkUtil();
//        String ip = ipadd.getIPAddress(request);
        String st = request.getParameter("Permission");
        if (st==null){
            st=request.getParameter("Permission1");
        }
        int status = Integer.parseInt(st);
        UserDao user = new UserDao();
        try {
            user.updateUserinfo(uid,username,nickname,email,password,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ss==null&&ss1==null) {
            response.sendRedirect("admin/user.html");
        }
        else{
            response.sendRedirect("login.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
