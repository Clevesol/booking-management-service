package com.enactor._server;

import com.enactor._server.core.ControllerDependencyFilteredRegistry;
import com.enactor._server.core.DependencyManager;
import com.enactor._server.core.annotation.Controller;
import com.enactor._server.core.annotation.Service;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.HandlesTypes;
import jakarta.servlet.http.HttpServlet;

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
//        System.out.println(";;;;;;;;;initializing server start up;;;;;;;;;;;;;;, classSet-> "+ declaredClasses);
//        if (declaredClasses != null) {
//            System.out.println(declaredClasses);
//            for (Class<?> clazz : declaredClasses) {
//                System.out.println("cls -> " + clazz.getName() +"\n");
//                if (clazz.getName().equals(DispatcherServlet.class.getName())) {
//                    try {

                        // Instantiate the discovered class


//                    } catch (Exception e) {
//                        throw new ServletException("Failed to instantiate " + clazz.getName(), e);
//                    }
//                }

//            }
//        }
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
