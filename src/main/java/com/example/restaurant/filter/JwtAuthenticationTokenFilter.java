package com.example.restaurant.filter;

import com.example.restaurant.pojo.LoginUser;
import com.example.restaurant.utils.JwtUtil;
import com.example.restaurant.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }


        // 解析token
        String userEmail;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userEmail = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("token illegal");
        }



        // 从redis中获取用户信息
        String redisKey = "login:" + userEmail;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)){
                    throw new RuntimeException("login unsuccessfully");
        }


        // 存入SecurityContextHolder
        // TODO 获取权限信息封装到authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);

    }
}
