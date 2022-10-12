package com.example.restaurant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.restaurant.entities.Table;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
@Mapper
public interface TableMapper extends BaseMapper<Table> {

    @Select("Select * from sys_table where restaurant_id = #{id} and deleted =0;")
    List<Table> getTableByRestId(@Param("id") int id);

    @Update("<script>" +
            "Update sys_table Set deleted = 1 where restaurant_id = #{id} and table_number in " +
            "<foreach item= 'num' index= 'index' collection= 'number' open= '(' separator= ',' close= ')'>" +
            "#{num}" +
            "</foreach>" +
            ";" +
            "</script>")
    void deleteCertainTables(@Param("id") int id, @Param("number") List<Integer> number);




    @Update("Update sys_booking Set table_id = #{tableId} where rest_id = #{restId}" +
            "And table_num = #{tableNumber} And date_time> #{time}")
    void substitute(@Param("tableId") String tableId, @Param("restId") String restId, @Param("tableNumber") String tableNumber, @Param("time") LocalDateTime time);


}
