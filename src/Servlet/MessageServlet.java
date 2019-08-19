package Servlet;

import dao.MessageDao;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Message")
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置所有类型的请求都可访问
        response.addHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String mid = request.getParameter("mid");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String all = request.getParameter("all");
        int status = Integer.parseInt(request.getParameter("status"));
//        System.out.println("-=-=-="+ name + status + all);
        PrintWriter out = response.getWriter();
//        if (!all.isEmpty()){
//
//        }
        if (all==null) {
            if (status == 1) {
                if (name.isEmpty()) {
                    try {
                        MessageDao message = new MessageDao();
                        JSONArray jsonarray = new JSONArray();
                        int page1 = Integer.parseInt(page);
                        int limit1 = Integer.parseInt(limit);
                        jsonarray = message.get_ListInfo(page1, limit1);
                        out = response.getWriter();
                        out.println(jsonarray);
                    } catch (Exception e) {
                        out.print("");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        MessageDao message = new MessageDao();
                        JSONArray jsonarray = new JSONArray();
                        jsonarray = message.mesname(name);
                        out = response.getWriter();
                        out.println(jsonarray);
                    } catch (Exception e) {
                        out.print("");
                        e.printStackTrace();
                    }
                }
            } else {
                if (mid.length() > 0) {
                    try {
                        int mi = Integer.parseInt(mid);
                        MessageDao message = new MessageDao();
                        JSONArray jsonarray = new JSONArray();
                        jsonarray = message.mes_info(mi);
                        out = response.getWriter();
                        out.println(jsonarray);
                    } catch (Exception e) {
                        out.print("");
                        e.printStackTrace();
                    }
                }
            }
        }
        else  {
            try {
                MessageDao message = new MessageDao();
                JSONArray jsonarray = new JSONArray();
                int page1 = Integer.parseInt(page);
                int limit1 = Integer.parseInt(limit);
                jsonarray = message.get_ListInfo(-1, limit1);
                out = response.getWriter();
                out.println(jsonarray);
            } catch (Exception e) {
                out.print("");
                e.printStackTrace();
            }
        }
    }
}
