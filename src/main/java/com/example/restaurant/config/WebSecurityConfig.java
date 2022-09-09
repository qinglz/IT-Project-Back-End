//package com.example.restaurant.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //配置认证方式等
//        super.configure(auth);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //http相关的配置，包括登入登出、异常处理、会话管理等
//        super.configure(http);
//    }
//}