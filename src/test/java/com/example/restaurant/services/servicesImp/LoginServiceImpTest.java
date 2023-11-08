package com.example.restaurant.services.servicesImp;

import com.example.restaurant.entities.LoginUser;
import com.example.restaurant.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoginServiceImpTest {
    @Autowired
    LoginServiceImp loginServiceImp;
    @Autowired
    LoginMapper loginMapper;

    @Test
    void userLogin() {
        Map<String,String> info = new HashMap<>();
        info.put("email","zql20000924@163.com");
        info.put("password","Zql20000924");
        System.out.println(loginServiceImp.userLogin(info));
        //Test passed, password checking correct,and new token stored;

    }

    @Test
    void userLogout() {
        LoginUser loginUser = new LoginUser(loginMapper.selectByEmail("zql20000924@163.com"));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmMWNmODFiZDM3NTU0Mzk4ODBlZmFlMzJkY2NiOWJjMSIsInN1YiI6InpxbDIwMDAwOTI0QDE2My5jb20iLCJpc3MiOiJzZyIsImlhdCI6MTY2NjEwMTg3NywiZXhwIjoxNjY2MTA1NDc3fQ.1z6SYD920pOnmxFS4qtMDWR7FffQUUm6TOCt5VjJ39Q";
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, token, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        loginServiceImp.userLogout();
        //Test passed, the login record with this token has been deleted.
    }

    @Test
    void userSignUp() {
        Map<String,String> info = new HashMap<>();
        info.put("email","test@163.com");
        info.put("password","Zql20000924");
        info.put("fullName","unitest");
        System.out.println(loginServiceImp.userSignUp(info));
        //Test passed, new account has been created and automatically login, returning a token.


    }
}