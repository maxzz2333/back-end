package com.unsw.waitsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unsw.waitsystem.entity.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TableMapper extends BaseMapper<Table> {

    @Select("select * from tables")
    public List<Table> getAllTables();

    @Update("update tables set table_state = 1 where table_id = #{tableId}")
    public void selectTable(int tableId);

    @Update("update tables set table_state = 0 where table_id = #{tableId}")
    public void leaveTable(int tableId);
}
