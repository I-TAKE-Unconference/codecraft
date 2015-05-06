package com.itakeunconf.codecraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.itakeunconf.codecraft"})
public class CodeCraftApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeCraftApplication.class);
    }
}