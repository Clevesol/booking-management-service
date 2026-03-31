package com.enactor._server;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class BookingServiceContextListener implements ServletContextListener {
    /**
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);

    }

    /**
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
