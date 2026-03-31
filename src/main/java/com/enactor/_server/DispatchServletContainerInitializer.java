package com.enactor._server;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.HandlesTypes;
import jakarta.servlet.http.HttpServlet;

import java.util.Set;

@HandlesTypes(HttpServlet.class)
public class DispatchServletContainerInitializer implements ServletContainerInitializer {


    /**
     * @param declaredClasses
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> declaredClasses, ServletContext servletContext) throws ServletException {

        System.out.println(";;;;;;;;;initializing server start up;;;;;;;;;;;;;;, classSet-> "+ declaredClasses);
        if (declaredClasses != null) {
            System.out.println(declaredClasses);
            for (Class<?> clazz : declaredClasses) {
                System.out.println("cls -> " + clazz.getName() +"\n");
                if (clazz.getName().equals(DispatcherServlet.class.getName())) {
                    try {

                        // Instantiate the discovered class
                        DispatcherServlet servlet = (DispatcherServlet) clazz.getDeclaredConstructor().newInstance();

                        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(servlet.getClass().getName(), servlet);
                        servletRegistration.addMapping(servlet.getBaseAPIURL());
                        servletRegistration.setLoadOnStartup(1);

                    } catch (Exception e) {
                        throw new ServletException("Failed to instantiate " + clazz.getName(), e);
                    }
                }

            }
        }
    }
}
