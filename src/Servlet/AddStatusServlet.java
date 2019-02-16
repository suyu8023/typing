package Servlet;

import dao.ContestDao;
import dao.MessageDao;
import dao.StatusDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddStatus")
public class AddStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        int uid = (int) session.getAttribute("uid");
        String name =(String) session.getAttribute("name");
        String username =(String) session.getAttribute("username");
        String speed= request.getParameter("speed1");
        String times= request.getParameter("times1");
        String times1= request.getParameter("times2");
        String word = request.getParameter("wordsnum1");
        String instan = request.getParameter("instan");

//        System.out.println(word.length());
        if (word.length()>0) {
            int wordsnum = Integer.parseInt(request.getParameter("wordsnum1"));
            String correct_rate = request.getParameter("correct_rate1");
            String wrongnum = request.getParameter("wrongnum1");
            String mes_name = request.getParameter("mes_name1");
            int mid = Integer.parseInt(request.getParameter("mid"));
            double spe = Double.parseDouble(speed);
            double correct = Double.parseDouble(correct_rate);
            String str = "不及格,加强练习!";
            if (spe >= 200.0 && correct >= 95.0) {
                str = "优秀,继续保持!";
            } else if (spe >= 170.0 && correct >= 95.0) {
                str = "良好,继续加油!";
            } else if (spe >= 110.0 && correct >= 95.0) {
                str = "及格,继续努力!";
            }
            double speed1 = Double.parseDouble(speed);
            double correct_rate1 = Double.parseDouble(correct_rate);
            StatusDao status = new StatusDao();
            try {
                status.addstatus(uid, mid, username, name, mes_name, speed1, correct_rate1, str, wordsnum, times, times1, instan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("home/status.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
