package com.example.restaurant.mapper;

import com.example.restaurant.entities.BusinessUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class LoginMapperTest {

    @Autowired
    LoginMapper loginMapper;
    @Test
    void selectByEmail() {
        BusinessUser businessUser = loginMapper.selectByEmail("xwd222@gmail.com");
        System.out.println(businessUser);
    }
}