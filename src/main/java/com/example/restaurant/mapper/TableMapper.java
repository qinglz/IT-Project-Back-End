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
            "Update sys_table Set deleted = 1 where id in " +
            "<foreach item= 'id' index= 'index' collection= 'ids' open= '(' separator= ',' close= ')'>" +
            "#{id}" +
            "</foreach>" +
            ";" +
            "</script>")
    void deleteCertainTables(@Param("ids") List<Integer> ids);




    @Update("Update sys_booking Set table_id = #{tableId} where rest_id = #{restId}" +
            "And table_num = #{tableNumber} And date_time> #{time}")
    void substitute(@Param("tableId") String tableId, @Param("restId") String restId, @Param("tableNumber") String tableNumber, @Param("time") LocalDateTime time);

    @Update("Update sys_table Set xpos = #{xpos}, ypos = #{ypos} where id = #{id}")
    void updateATable(@Param("xpos") String xpos, @Param("ypos") String ypos, @Param("id") String id);

}
