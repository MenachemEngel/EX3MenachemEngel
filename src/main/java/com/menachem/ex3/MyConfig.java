package com.menachem.ex3;

import com.menachem.ex3.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class add the address to filter and the interceptor to the registry of interceptor.
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    /**
     * This function it's override function of WebMvcConfigurer that do what we tolled above.
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/*");
    }
}
