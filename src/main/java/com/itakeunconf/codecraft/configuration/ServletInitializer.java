package com.itakeunconf.codecraft.configuration;

import com.itakeunconf.codecraft.CodeCraftApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CodeCraftApplication.class);
    }
}