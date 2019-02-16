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

@WebServlet("/Contestinfo")
public class ContestinfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        response.setContentType("text/html;charset=UTF-8");
        int cid = Integer.parseInt(request.getParameter("cid"));
//        int page = Integer.parseInt(request.getParameter("page"));
//        int limit = Integer.parseInt(request.getParameter("limit"));
//        String sta= request.getParameter("sta");
//        if (cid==null) {
            PrintWriter out = response.getWriter();
            try {
                ContestDao contest = new ContestDao();
                JSONArray jsonarray = new JSONArray();
                jsonarray = contest.get_contestinfo(cid);
                out = response.getWriter();
                out.println(jsonarray);
            } catch (Exception e) {
                out.print("");
                e.printStackTrace();
            }
//        }
    }
}
