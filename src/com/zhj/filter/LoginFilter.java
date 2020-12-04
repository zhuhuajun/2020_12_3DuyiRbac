package com.zhj.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ：
 * @date ：Created in 2020/11/24 下午 04:29
 */

public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String method = request.getParameter("method");
        String uri = request.getRequestURI();

        if (uri.equals("/") || uri.indexOf("UserController.do") != -1 && method.equals("login") || uri.indexOf("UserController.do") != -1 && method.equals("exit") ||uri.indexOf("timeout.jsp") != -1 ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect("/timeout.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
