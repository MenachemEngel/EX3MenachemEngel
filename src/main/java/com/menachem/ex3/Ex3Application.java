package com.menachem.ex3;

import com.menachem.ex3.bean.SessionBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * This class is the main class of the program
 * it including the main function and declare
 * the bean scope.
 */
@SpringBootApplication
public class Ex3Application {

    /**
     * This function it's declare the bean scope.
     * @return new Session Bean class
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionBean MySessionBean() {
        return new SessionBean();
    }

    /**
     * This function it's the main functions.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Ex3Application.class, args);
    }

}
