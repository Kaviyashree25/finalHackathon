package com.stackroute.netflixzuulapigatewayserver.config;

import com.stackroute.netflixzuulapigatewayserver.jwtfilter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {
    //@Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/favourite-service/api/v1/*");
        return filterRegistrationBean;
    }
}
