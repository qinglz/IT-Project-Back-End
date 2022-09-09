package com.example.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.restaurant.pojo.Restaurant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchingMapper extends BaseMapper<Restaurant> {
    @Select("select * from sys_restaurant where name REGEXP #{name};")
    List<Restaurant> searchRestaurantByName(@Param("name") String name);

    @Select("select * from sys_restaurant where name = #{name};")
    List<Restaurant> checkRestaurantByName(@Param("name") String name);
}
