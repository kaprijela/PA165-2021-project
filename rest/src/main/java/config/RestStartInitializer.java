package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RestStartInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RestSpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/api/v1/*"};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

}