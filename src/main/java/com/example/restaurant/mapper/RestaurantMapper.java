package com.example.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.restaurant.entities.BusinessUser;
import com.example.restaurant.entities.Restaurant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RestaurantMapper extends BaseMapper<Restaurant> {
    @Select("select * from sys_restaurant where name REGEXP #{name} and deleted = 0;")
    List<Restaurant> searchRestaurantByName(@Param("name") String name);

//    @Select("select * from sys_restaurant where address = #{address};")
//    List<Restaurant> checkRestaurantByAddress(@Param("address") String address);

    @Select("select * from sys_restaurant where id = #{id};")
    Restaurant findRestaurantById(@Param("id") String id);

    @Select("Select * from sys_business_user where id = #{id};")
    BusinessUser findBusinessUserById(@Param("id") String id);

    @Select("select * from sys_restaurant where owner_id = #{id} and deleted= 0;")
    List<Restaurant> findRestaurantByOwnerId(@Param("id") String id);

    @Update("Update sys_restaurant Set deleted = 1 where id = #{id};")
    void deleteARestaurant(@Param("id") String id);

    @Update("Update sys_table Set deleted = 1 where restaurant_id = #{id};")
    void deleteTablesByRestaurant(@Param("id") String id);




}
