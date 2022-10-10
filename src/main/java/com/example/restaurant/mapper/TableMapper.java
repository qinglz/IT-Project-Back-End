package com.example.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.restaurant.entities.Table;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface TableMapper extends BaseMapper<Table> {

    @Select("Select * from sys_table where restaurant_id = #{id};")
    List<Table> getTableByRestId(@Param("id") int id);

    @Delete("<script>" +
            "Set FOREIGN_key_checks = 0;" +
            "Delete from sys_table where restaurant_id = #{id} and table_number in " +
            "<foreach item= 'num' index= 'index' collection= 'number' open= '(' separator= ',' close= ')'>" +
            "#{num}" +
            "</foreach>" +
            ";" +
            "Set FOREIGN_key_checks = 1;" +
            "</script>")
    void deleteACertainTable(@Param("id") int id, @Param("number") List<Integer> number);

}
