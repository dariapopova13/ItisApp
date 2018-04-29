package com.university.itis.itisapp.config;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.validation.constraints.NotNull;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                RootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration charEncodingFilterReg =
                servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
        charEncodingFilterReg.setInitParameter("forceEncoding", "true");
        charEncodingFilterReg.addMappingForUrlPatterns(null, false, "/*");
        super.onStartup(servletContext);
    }

    protected Filter[] getServletFilters() {

        // if encoding has issues we need to add UTF-8 encoding filter

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();

        encodingFilter.setForceEncoding(true);

        encodingFilter.setEncoding("UTF-8");

        // encoding filter must be the first one

        return new Filter[]{encodingFilter,

                new DelegatingFilterProxy("springSecurityFilterChain"),

                new OpenEntityManagerInViewFilter()};

    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
