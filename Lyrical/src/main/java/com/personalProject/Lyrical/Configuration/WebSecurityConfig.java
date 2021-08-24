package com.personalProject.Lyrical.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .and()
                .authorizeRequests(authorize -> authorize.mvcMatchers("/")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
                .oauth2Login()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}
