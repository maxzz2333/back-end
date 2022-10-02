package com.unsw.waitsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unsw.waitsystem.entity.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StaffMapper extends BaseMapper<Staff> {
    @Select("select * from staffs where email = #{email}")
    public Staff getStaffInfoByEmail(String email);

    @Select("select * from staffs where staff_id = #{staffId}")
    public Staff getStaffInfoById(Long staffId);

    @Update("update staffs set password = #{password} where staff_id = #{staffId}")
    public void updateStaffPassword(Long staffId,String password);
}
