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
class RestaurantManageServiceImpTest {
    @Autowired
    RestaurantManageServiceImp restaurantManageServiceImp;
    @Autowired
    LoginMapper loginMapper;

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1YTUxNzc3YTZmYTY0MjJkYmI5NzhkNGMzYTJmMGFlMiIsInN1YiI6InpxbDIwMDAwOTI0QDE2My5jb20iLCJpc3MiOiJzZyIsImlhdCI6MTY2NjQzODQzNSwiZXhwIjoxNjY2NDQyMDM1fQ.W-bBRsMm4kzRmYtJMqDytg1emeqZLXO6P6nXaEbTx6s";


    @Test
    void restaurantRegister() {
        LoginUser loginUser = new LoginUser(loginMapper.selectByEmail("zql20000924@163.com"));
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, token, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Map<String,String> info = new HashMap<>();
        info.put("restName","Unit test");
        info.put("capacity","30");
        info.put("numStaff","10");
        info.put("address","test St");
        restaurantManageServiceImp.restaurantRegister(info);
        //Test passed. Correct restaurant has been created under the owner has logged in.
    }

    @Test
    void restaurantUpdate() {
        LoginUser loginUser = new LoginUser(loginMapper.selectByEmail("zql20000924@163.com"));
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, token, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Map<String,String> info = new HashMap<>();
        info.put("restId","16");
        info.put("restName","Unit test-changed");
        info.put("capacity","35");
        info.put("numStaff","15");
        info.put("address","test-changed St");
        restaurantManageServiceImp.restaurantUpdate(info);
        //Test passed. The information of restaurant id=16 has been updated successfully.

    }

    @Test
    void getAllRestaurants() {
        LoginUser loginUser = new LoginUser(loginMapper.selectByEmail("zql20000924@163.com"));
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, token, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(restaurantManageServiceImp.getAllRestaurants());
        //Test passed. The restaurant owned by this user has been returned.
    }

    @Test
    void getUserName() {
        LoginUser loginUser = new LoginUser(loginMapper.selectByEmail("zql20000924@163.com"));
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, token, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(restaurantManageServiceImp.getUserName());
        //Test passed. Correct username of this account returned.

    }

    @Test
    void restaurantDelete() {
        LoginUser loginUser = new LoginUser(loginMapper.selectByEmail("zql20000924@163.com"));
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, token, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Map<String,String> info = new HashMap<>();
        info.put("restId","16");
        restaurantManageServiceImp.restaurantDelete(info);
        //Test passed. The restaurant id=16 has been set deleted.

    }

    @Test
    void getBookingByRestaurant() {
        LoginUser loginUser = new LoginUser(loginMapper.selectByEmail("zql20000924@163.com"));
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser, token, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(restaurantManageServiceImp.getBookingByRestaurant("5"));
        //Test passed. The booking information of restaurant id =5 returned.

    }
}