package com.example.restaurant;

import com.example.restaurant.controllers.BookingController;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Table;
import com.example.restaurant.mapper.BookingMapper;
import com.example.restaurant.mapper.LoginMapper;
import com.example.restaurant.services.BookingService;
import com.example.restaurant.utils.TableAllocation;
import com.example.restaurant.utils.TableCapacityComparator;
import com.example.restaurant.utils.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    BookingController bookingController;
    @Autowired
    BookingService bookingService;
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
    @Test void testAvailableTableSearching(){
        LocalDateTime localDateTime1 = LocalDateTime.of(2022,9,22,13,30,0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2022,9,23,15,30,0);
        String from = TimeUtil.toSqlDateTime(localDateTime1);
        String to = TimeUtil.toSqlDateTime(localDateTime2);
        System.out.println(LocalDateTime.parse("2022-09-22T14:30:00"));
    }
    @Test
    void testComparator(){
        LocalDateTime localDateTime1 = LocalDateTime.of(2022,9,2,13,30,0);
        List<Table> tables = bookingService.getAvailableTable(1,localDateTime1);
        Collections.sort(tables,new TableCapacityComparator());
        System.out.println(tables);

    }
    @Test
    void testAllocate(){
        LocalDateTime localDateTime1 = LocalDateTime.of(2022,9,2,13,30,0);
        List<Table> tables = bookingService.getAvailableTable(1,localDateTime1);
        Collections.sort(tables,new TableCapacityComparator());
        List<Table> allocatedTables = TableAllocation.allocate(10,tables);
        System.out.println(tables);
        System.out.println(allocatedTables);
    }
    @Test
    void bookingDtoTest(){
        System.out.println(bookingMapper.getBookingsByRestId(1));
    }

}
