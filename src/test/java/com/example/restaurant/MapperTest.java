package com.example.restaurant;

import com.example.restaurant.mapper.LoginMapper;
import com.example.restaurant.pojo.BusinessUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



        System.out.println(passwordEncoder.matches("1234",
                "$2a$10$nrAmcbNPJFr2Tisjk.1arOnqiBukepaC50NAeVG05YRra8aIoEzUq"));

//        System.out.println(passwordEncoder.matches("1234",
//                "$2a$10$NKCwYrjGDfkteM7p22m5veUB8ery2uV/AampXJylbqBZtdn0PAeGi"));


//        $2a$10$NKCwYrjGDfkteM7p22m5veUB8ery2uV/AampXJylbqBZtdn0PAeGi
        String encode = passwordEncoder.encode("2222jZ222");
//        String encode2 = passwordEncoder.encode("1234");
//
        System.out.println(encode);
//        System.out.println(encode2);
    }
    @Test
    public void testRedisTemplate(){
        String s = redisTemplate.opsForValue().get("aaa").toString();
        System.out.println(s);
    }
    @Test
    public void deleteValue(){
        redisTemplate.delete("name");
    }
    @Test
    public void setNewValue(){
        redisTemplate.opsForValue().set("aaa","dawdsadwdsdsds");
    }

}
