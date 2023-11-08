package com.example.restaurant.mapper;

import com.example.restaurant.entities.BusinessUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoginMapperTest {

    @Autowired
    LoginMapper loginMapper;
    @Test
    void selectByEmail() {
        BusinessUser businessUser = loginMapper.selectByEmail("zql20000924@163.com");
        System.out.println(businessUser);
    }
}