package com.idalavye.petclinic.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "TestServlet")
public class TestFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.getWriter().write("Before ...");
        chain.doFilter(req, resp);
        resp.getWriter().write("After ...");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
