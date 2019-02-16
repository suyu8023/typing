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

@WebServlet("/home/UserContestRank")
public class UserSontestRankServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        response.setContentType("text/html;charset=UTF-8");
        String csid = request.getParameter("csid");
//        String uid = request.getParameter("uid");
//        int page = Integer.parseInt(request.getParameter("page"));
//        int limit = Integer.parseInt(request.getParameter("limit"));
//        System.out.print(cid+"-="+page+"-="+limit);
//        String sta= request.getParameter("sta");
        PrintWriter out = response.getWriter();
        if (csid==null) {
            response.sendRedirect("home/contest.html");
        }
        else{
            int mid= Integer.parseInt(csid);
//            int uuid= Integer.parseInt(uid);
//            if (uid != null) {
//                int ui = Integer.parseInt(uid);
            try {
                ContestDao contest = new ContestDao();
                JSONArray jsonarray = new JSONArray();
                jsonarray = contest.get_usercontestrank(mid);
                out.println(jsonarray);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            }
//            else {
//                try {
//                    StatusDao status = new StatusDao();
//                    JSONArray jsonarray = new JSONArray();
////                    int limit1 = Integer.parseInt(limit);
//                    jsonarray = status.get_ListInfo(page, limit,sta1);
//                    out.println(jsonarray);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
        }
    }
}
