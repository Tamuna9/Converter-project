package com.example.My_Converter_project;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//configuration for setting up view resolvers
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void  configureViewResolvers(ViewResolverRegistry registry){
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
}
