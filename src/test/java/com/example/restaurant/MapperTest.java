package com.example.restaurant;

import com.alibaba.fastjson.parser.SymbolTable;
import com.example.restaurant.controllers.BookingController;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Table;
import com.example.restaurant.mapper.BookingMapper;
import com.example.restaurant.mapper.LoginMapper;
import com.example.restaurant.mapper.TableMapper;
import com.example.restaurant.services.BookingService;
import com.example.restaurant.services.LoginService;
import com.example.restaurant.services.SearchingService;
import com.example.restaurant.utils.JwtUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTest {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private LoginService loginService;
    @Autowired
    SearchingService searchingService;
    @Autowired
    BookingController bookingController;
    @Autowired
    BookingService bookingService;
    @Autowired
    TableMapper tableMapper;
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
    public void testJWT(){
        String test = "zzz";
        String jwt1 = JwtUtil.createJWT(test);
        String jwt2 = JwtUtil.createJWT(test);
        System.out.println(jwt1);
        System.out.println(jwt2);


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

    @Test
    void updateTableTest(){
        Map<String,String> info = new HashMap<>();
        info.put("email","zql20000924@163.com");
        info.put("password","Zql20000924");

        String s = "chicken";
        tableMapper.updateATable("100","200","64");


    }

}
