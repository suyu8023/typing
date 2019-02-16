package Servlet;

import bean.User;
import dao.UserDao;
import util.NetworkUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取账号和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        NetworkUtil ipadd = new NetworkUtil();
        String ip = ipadd.getIPAddress(request);
        String login_time = request.getParameter("upd_time");
//        System.out.println("---"+username+password+ip+login_time);
        User admin = new User();
        UserDao admindao = new UserDao();
        try {
            admin = admindao.getAdminInfo(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(ip);
        UserDao user = new UserDao();
        try {
            user.updateUserip(admin.getUid(),login_time,ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("uid",admin.getUid());
        session.setAttribute("username",admin.getUsername());
        session.setAttribute("name",admin.getName());
        session.setAttribute("status",admin.getStatus());
        session.setAttribute("email",admin.getEmail());
        session.setMaxInactiveInterval(60 * 60);
        response.sendRedirect("home/index.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = (String) session.getAttribute("name");
        String username1 = (String) session.getAttribute("username");
        String uid =  Integer.toString((Integer) session.getAttribute("uid"));
        String status =  Integer.toString((Integer) session.getAttribute("status"));
        String email = (String) session.getAttribute("email");
//        String username = (String) session.getAttribute("username");
        PrintWriter out = response.getWriter();
        if (username!=null&&password!=null){
            User admin = new User();
            UserDao admindao = new UserDao();
            try {
                admin = admindao.getAdminInfo(username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println(admin.getName());
            if (admin.getName() == null){
                out.println("false");
            }
            else{
                out.println("true");
            }

        }
        else if(username==null&&password==null){
            String str= uid+'-'+username1+'-'+name+'-'+email+'-'+status;
            out.println(str);
        }

//        System.out.println("+++"+name);
    }
}
