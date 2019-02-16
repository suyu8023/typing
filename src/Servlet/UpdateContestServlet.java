package Servlet;

import dao.ContestDao;
import dao.MessageDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateContest")
public class UpdateContestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        int cid = Integer.parseInt(request.getParameter("cid"));
        String contests_name =request.getParameter("contests_name");
        String mesname = request.getParameter("mesname");
        String[] str = mesname.split("-");
//        int mid = Integer.parseInt(str[0]);
        String ss=str[0];
        int midd = Integer.parseInt(ss);
        String begin_time = request.getParameter("begin_time");
        String end_time = request.getParameter("end_time");
        int times = Integer.parseInt(request.getParameter("times"));
//        NetworkUtil ipadd = new NetworkUtil();
//        String ip = ipadd.getIPAddress(request);
//        int status = Integer.parseInt(request.getParameter("Permission"));
        ContestDao contest = new ContestDao();
        try {
            contest.updateContestinfo(cid,contests_name,name,midd,str[1],begin_time,end_time,times);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin/contest.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
