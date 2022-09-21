package com.example.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
            "t where t.restaurant_id = #{restId} and\n" +
            "NOT EXISTS (Select * From `sys_booking` as b WHERE b.table_id = t.id and b.datetime BETWEEN #{from} and\n" +
            "#{to});")
    public List<Table> getAvailableTable(@Param("restId") int restId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Select("SELECT Sum(capacity) FROM sys_table as\n" +
            "t where t.restaurant_id = #{restId} and\n" +
            "NOT EXISTS (Select * From `sys_booking` as b WHERE b.table_id = t.id and b.datetime BETWEEN #{from} and\n" +
            "#{to});")
    public Integer getAvailableCapacity(@Param("restId") int restId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);


    @Select("Select * from sys_booking")
    public List<Booking> getBookings();

    @Select("SELECT Sum(capacity) FROM sys_table as\n" +
            "t where t.restaurant_id = #{restId} and\n" +
            "EXISTS (Select * From `sys_booking` as b WHERE b.table_id = t.id and b.datetime BETWEEN #{from} and\n" +
            "#{to});")
    public Integer getUsedCapacity(@Param("restId") int restId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
