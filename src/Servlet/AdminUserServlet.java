package Servlet;

import dao.MessageDao;
import dao.UserDao;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AdminUser")
public class AdminUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String uid = request.getParameter("uid");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        int status = Integer.parseInt(request.getParameter("status"));

        PrintWriter out = response.getWriter();
        if(status==1) {
            if (name.isEmpty()) {
                try {
                    UserDao user = new UserDao();
                    JSONArray jsonarray = new JSONArray();
                    int page1= Integer.parseInt(page);
                    int limit1 = Integer.parseInt(limit);
                    jsonarray = user.get_userlist(page1,limit1);
                    out = response.getWriter();
                    out.println(jsonarray);
                } catch (Exception e) {
                    out.print("");
                    e.printStackTrace();
                }
            }
            else{
                try {
                    UserDao user = new UserDao();
                    JSONArray jsonarray = new JSONArray();
                    jsonarray = user.user_info1(name);
                    out = response.getWriter();
                    out.println(jsonarray);
                } catch (Exception e) {
                    out.print("");
                    e.printStackTrace();
                }
            }
        }
        else{
            if (uid.length()>0) {
                try {
                    int ui = Integer.parseInt(uid);
                    UserDao user = new UserDao();
                    JSONArray jsonarray = new JSONArray();
                    jsonarray = user.user_info(ui);
                    out = response.getWriter();
                    out.println(jsonarray);
                } catch (Exception e) {
                    out.print("");
                    e.printStackTrace();
                }
            }
        }
    }
}
