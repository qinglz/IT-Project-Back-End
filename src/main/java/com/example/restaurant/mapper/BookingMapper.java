package com.example.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.restaurant.Dto.BookingDto;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Table;
import jdk.jfr.Enabled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BookingMapper extends BaseMapper<Booking> {
    @Select("SELECT * FROM sys_table as\n" +
            "t where t.restaurant_id = #{restId} and t.deleted = 0 and \n" +
            "NOT EXISTS (Select * From `sys_booking` as b WHERE b.table_id = t.id and b.date_time BETWEEN #{from} and\n" +
            "#{to});")
    public List<Table> getAvailableTable(@Param("restId") int restId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Select("SELECT Sum(capacity) FROM sys_table as\n" +
            "t where t.restaurant_id = #{restId} and t.deleted = 0 and\n" +
            "NOT EXISTS (Select * From `sys_booking` as b WHERE b.table_id = t.id and b.date_time BETWEEN #{from} and\n" +
            "#{to});")
    public Integer getAvailableCapacity(@Param("restId") int restId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);



    @Select("SELECT r.`name` as restaurant_name, customer_name, customer_phone_number,customer_email,\n" +
            "date_time, table_number,num_people,b.id as id\n" +
            "FROM `sys_booking` as b INNER JOIN `sys_table`as t ON\n" +
            "b.table_id = t.id INNER JOIN `sys_restaurant` as r On\n" +
            "t.restaurant_id = r.id where t.restaurant_id = #{restId}")
    public List<BookingDto> getBookingsByRestId(@Param("restId") int restId);

    @Select("SELECT Sum(capacity) FROM sys_table as\n" +
            "t where t.restaurant_id = #{restId} and t.deleted=0 and\n" +
            "EXISTS (Select * From `sys_booking` as b WHERE b.table_id = t.id and b.date_time BETWEEN #{from} and\n" +
            "#{to});")
    public Integer getUsedCapacity(@Param("restId") int restId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
    @Select("SELECT * from sys_booking where customer_phone_number=#{phone} and\n" +
            "date_time BETWEEN #{from} and #{to};")
    public List<Booking> getBookingByUserPhoneAndTime(@Param("phone") String phone, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Select("SELECT * from sys_booking where customer_email=#{email} and\n" +
            "date_time BETWEEN #{from} and #{to};")
    public List<Booking> getBookingByUserEmailAndTime(@Param("email") String email, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
