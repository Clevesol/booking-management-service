package com.enactor.appcore.appserver;

import com.enactor.appcore.appserver.core.didc.DependencyManager;
import com.enactor.appcore.appserver.core.annotation.Controller;
import com.enactor.appcore.appserver.core.annotation.Service;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes({Controller.class, Service.class})
public class DispatchServletContainerInitializer implements ServletContainerInitializer {


    /**
     * @param declaredClasses
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> declaredClasses, ServletContext servletContext) throws ServletException {
        this.init(declaredClasses);
        DispatcherServlet servlet = DispatcherServlet.getInstance();
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(servlet.getClass().getName(), servlet);
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);
    }

    /**
     * initializes the server components
     */
    private void init(Set<Class<?>> declaredClasses){
        DependencyManager dependencyManager = DependencyManager.getInstance();
        try {
            dependencyManager.init(declaredClasses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
