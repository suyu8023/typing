package Servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/Filter")
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        //获取请求路径
        String path = req.getRequestURI();
        //获取session中作为判断的字段
        String pwd = (String) session.getAttribute("username");
//        System.out.println("/*/*/*/*"+status);
        //判断请求的 路径中是否包含了 登录页面的请求
        //如果包含了，那么不过滤 继续执行操作

        if (path.contains("index.html")&&!path.contains("home")&&!path.contains("admin")) {

            filterChain.doFilter(req, resp);
        }
        else {

            //如不包含，那么就要判断 session中否有标志，如果没有标志，那么不让他看，让他去登录，反之执行操作！
            if (pwd == null || "".equals(pwd)) {
                PrintWriter out = response.getWriter();
                out.write("<script type='text/javascript'>alert('请登录!');location.href='"+"../login.html';  </script>");
//                resp.sendRedirect("index.jsp");
            } else {
                int status = (int) session.getAttribute("status");

                if (path.contains("admin")&&status==2){
//                    System.out.println("-=-=-="+status);
                    PrintWriter out = response.getWriter();
                    out.write("<script type='text/javascript'>alert('抱歉,你不是管理员,请重新登录!');location.href='"+"../login.html';  </script>");
//                    resp.sendRedirect("index.jsp");
                }
                filterChain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
