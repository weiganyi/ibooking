package com.ibooking.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInitServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
    }

    public void init() throws ServletException {
        System.setProperty("webappRoot", getServletContext().getRealPath("/"));
        PropertyConfigurator.configure(getServletContext().getRealPath("/") + getInitParameter("configfile"));
    }
}