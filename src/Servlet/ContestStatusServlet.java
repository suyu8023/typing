package Servlet;

import dao.ContestDao;
import dao.StatusDao;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home/Contest/status")
public class ContestStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        response.setContentType("text/html;charset=UTF-8");
        String cid = request.getParameter("cid");
        String uid = request.getParameter("uid");
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
//        System.out.print(cid+"-="+page+"-="+limit);
//        String sta= request.getParameter("sta");
        PrintWriter out = response.getWriter();
        if (cid == null) {
            response.sendRedirect("home/contest.html");
        } else {
            if (uid == null) {
                int mid = Integer.parseInt(cid);
                try {
                    ContestDao contest = new ContestDao();
                    JSONArray jsonarray = new JSONArray();
                    jsonarray = contest.get_contestrank(mid, page, limit);
                    out.println(jsonarray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                int uuid= Integer.parseInt(uid);
                int mid = Integer.parseInt(cid);
                ContestDao contest = new ContestDao();
                JSONArray jsonarray = new JSONArray();
                try {
                    jsonarray = contest.get_usercontestranks(mid,uuid,page,limit);
                    out.println(jsonarray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
//}
