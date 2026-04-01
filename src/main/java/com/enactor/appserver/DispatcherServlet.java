package com.enactor.appserver;

import com.enactor.appserver.interceptor.RequestInterceptor;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestInterceptor interceptor = new RequestInterceptor();

    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String method = req.getMethod();
        try {
            this.interceptor.intercept(path, method, req, res);
        } catch (Exception e) {
            log.error("{}" ,e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    /**
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("works");
        super.doGet(req, resp);
    }


    private static DispatcherServlet dispatcherServlet;

    /**
     * Returns a singleton object of DispatcherServlet
     *
     * @return dispatcherServlet
     */
    public static DispatcherServlet getInstance() {
        if (null == dispatcherServlet) dispatcherServlet = new DispatcherServlet();
        return dispatcherServlet;
    }
}
