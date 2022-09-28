package com.example.restaurant.handler;

import com.alibaba.fastjson.JSON;
import com.example.restaurant.Result;
import com.example.restaurant.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint{
    public AuthenticationEntryPointImpl(){

    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = Result.error("You need to log in!");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);

    }


}
