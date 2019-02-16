package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home/Usercontest")
public class UsercontestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        String uid = request.getParameter("uid");
        String csid = request.getParameter("csid");
        String sid = request.getParameter("sid");
//        String name = request.getParameter("name");
//        String diff = request.getParameter("diff");
//        String message = request.getParameter("message");
//        request.setAttribute("mid",uid);
        if (csid==null) {
            request.setAttribute("sid", sid);
        }
        else if (sid==null){
            request.setAttribute("csid", csid);
        }
//        request.setAttribute("diff",diff);
//        request.setAttribute("message",message);
        request.getRequestDispatcher("curve.html").forward(request,response);
    }
}
