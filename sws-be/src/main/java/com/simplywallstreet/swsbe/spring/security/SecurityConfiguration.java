package com.simplywallstreet.swsbe.spring.security;

import com.simplywallstreet.swsbe.controller.CompanyController;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.GET, CompanyController.BASE_URL)
                .antMatchers(HttpMethod.GET, CompanyController.ENDPOINT_GET_COMPANY);
    }
}
