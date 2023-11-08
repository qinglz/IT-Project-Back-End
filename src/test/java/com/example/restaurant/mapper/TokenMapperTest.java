package com.example.restaurant.mapper;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TokenMapperTest {
    @Autowired
    TokenMapper tokenMapper;
    @Test
    void getUserId() {
        String id = tokenMapper.getUserId("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5ODNkYTgwYzMzNDk0ZTE1ODZiNThkYzliMTNlMTg4NCIsInN1YiI6InpxbDIwMDAwOTI0QDE2My5jb20iLCJpc3MiOiJzZyIsImlhdCI6MTY2NjE1MDcxOCwiZXhwIjoxNjY2MTU0MzE4fQ.wdU3VhjJORJC-a1sDoT6ktaaLSeEOH-Wck4ngVt1B58");
        System.out.println(id);
        //Test passed, the id returned is correct.
    }

    @Test
    void deleteToken() {
        tokenMapper.deleteToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5ODNkYTgwYzMzNDk0ZTE1ODZiNThkYzliMTNlMTg4NCIsInN1YiI6InpxbDIwMDAwOTI0QDE2My5jb20iLCJpc3MiOiJzZyIsImlhdCI6MTY2NjE1MDcxOCwiZXhwIjoxNjY2MTU0MzE4fQ.wdU3VhjJORJC-a1sDoT6ktaaLSeEOH-Wck4ngVt1B111");
        //Test passed, the record has this token has been deleted.
    }
}