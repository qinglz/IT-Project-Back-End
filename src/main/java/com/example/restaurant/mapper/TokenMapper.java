package com.example.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.restaurant.entities.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TokenMapper extends BaseMapper<Token> {
    @Select("Select user_id from sys_token where token = #{token}")
    public String getUserId(@Param("token")String token);
}
