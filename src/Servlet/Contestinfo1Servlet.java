package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/Contestinfo1")
public class Contestinfo1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String cid = request.getParameter("cid");
        String name = request.getParameter("name");
        String diff = request.getParameter("diff");
        String message = request.getParameter("message");
        request.setAttribute("cid",cid);
        request.setAttribute("name",name);
        request.setAttribute("diff",diff);
        request.setAttribute("message",message);
        request.getRequestDispatcher("contestinfo.html").forward(request,response);
    }
}
