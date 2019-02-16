package Servlet;

import dao.ContestDao;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/home/Contest")
public class ContestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        response.setContentType("text/html; charset=utf-8");;
        String cid = request.getParameter("cid");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

//        String sta= request.getParameter("sta");
        if (cid==null) {
//            System.out.println("qwert");
            PrintWriter out = response.getWriter();
            try {
                int page1= Integer.parseInt(page);
                int limit1 = Integer.parseInt(limit);
                ContestDao contest = new ContestDao();
                JSONArray jsonarray = new JSONArray();
                jsonarray = contest.get_contest(page1, limit1);
                out = response.getWriter();
                out.println(jsonarray);
            } catch (Exception e) {
                out.print("");
                e.printStackTrace();
            }
        }
        else{
//            System.out.println("-=-=-=");
            int cid1= Integer.parseInt(cid);
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String time = formatter.format(currentTime);
            ContestDao contest = new ContestDao();
            String[] str1 = new String[5];
            try {
                String str = contest.get_time(cid1);
                str1 =str.split("--");
//                System.out.println(str1[0]+str1[1]+str1[2]);
//                System.out.println(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (time.compareTo(str1[0])>=0&&time.compareTo(str1[1])<=0)
            {
                request.setAttribute("cid",cid1);
                request.getRequestDispatcher("contestread.html").forward(request,response);
            }
            else{
                response.sendRedirect("contest.html");
            }
        }
    }
}
