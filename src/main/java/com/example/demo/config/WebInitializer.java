package com.example.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    protected Class<?>[] getServletConfigClasses() {
//        return new Class[0];
        return null;
    }

    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
