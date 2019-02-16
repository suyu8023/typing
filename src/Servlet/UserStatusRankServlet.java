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

@WebServlet("/home/UserStatusRank")
public class UserStatusRankServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        response.setContentType("text/html;charset=UTF-8");
        String sid = request.getParameter("sid");
//        String uid = request.getParameter("uid");
//        int page = Integer.parseInt(request.getParameter("page"));
//        int limit = Integer.parseInt(request.getParameter("limit"));
//        System.out.print(cid+"-="+page+"-="+limit);
//        String sta= request.getParameter("sta");
        PrintWriter out = response.getWriter();
        if (sid==null) {
            response.sendRedirect("home/staus.html");
        }
        else{
            int mid= Integer.parseInt(sid);
//            int uuid= Integer.parseInt(uid);
//            if (uid != null) {
//                int ui = Integer.parseInt(uid);
            try {
                StatusDao status = new StatusDao();
                JSONArray jsonarray = new JSONArray();
                jsonarray = status.get_userstatusrank(mid);
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
