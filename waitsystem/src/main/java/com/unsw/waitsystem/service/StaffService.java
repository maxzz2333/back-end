package com.unsw.waitsystem.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unsw.waitsystem.entity.Staff;
import com.unsw.waitsystem.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("staffService")
public class StaffService{
    
    @Autowired
    @Qualifier("staffMapper")
    private StaffMapper staffMapper;

    HashMap<String, Object> data = new HashMap<String, Object>() {{
        put("staffId", null);
        put("name", null);
        put("position", null);
    }};

    HashMap<String, Object> errorMessage = new HashMap<String, Object>(){{
        put("errorMessage", null);
    }};

    /**
     * Registration rules:
     * 1.Each emalil can only register one time
     * 2.Password and email formatting requirements (not completed)
     * @param staff
     * @return {"userId","name","position"} if success
     */
    public Map<String, Object> register(Staff staff) {
        Staff staffInfo = staffMapper.getStaffInfoByEmail(staff.getEmail());
        if(staffInfo != null){
            errorMessage.put("errorMessage","email already registered");
            return errorMessage;
        }else if (!checkPasswordFormat(staff.getPassword())) {
            return errorMessage;
        }else {
            staffMapper.insert(staff);
            setStaffDataByEmail(staff.getEmail());
            return data;}
    }

    /**
     *
     * @param email
     * @param password
     * @return {"staffId","name","position"} if success
     */
    public Map<String, Object> logIn(String email, String password) {
        Staff staffInfo = staffMapper.getStaffInfoByEmail(email);
        if(staffInfo == null){
            errorMessage.put("errorMessage","Email Not Exist");
            return errorMessage;
        } else if (!staffInfo.getPassword().equals(password)) {
            errorMessage.put("errorMessage","Wrong Password");
            return errorMessage;
        }else {
            setStaffData(staffInfo);
            return data;}
    }

    public Map<String, Object> changePassword(Long id, String password) {
        Staff staffInfo = staffMapper.getStaffInfoById(id);
        if(staffInfo.getPassword().equals(password)){
            errorMessage.put("errorMessage","The new password cannot be the same as the old password");
            return errorMessage;
        } else if (!checkPasswordFormat(password)) {
            return errorMessage;
        } else {
            staffMapper.updateStaffPassword(id,password);
            setStaffData(staffInfo);
            return data;}
    }

    public boolean checkPasswordFormat(String password){
        if (password.length() > 20 || password.length() < 6) {
            errorMessage.put("errorMessage","Password length should be between 6 and 20");
            return false;
        }else {return true;}
    }


    public void setStaffDataByEmail(String email){
        Staff staffInfo = staffMapper.getStaffInfoByEmail(email);
        setData(staffInfo.getStaffId(), staffInfo.getEmail(), staffInfo.getPosition());
    }

    public void setStaffData(Staff staffInfo){
        setData(staffInfo.getStaffId(), staffInfo.getEmail(), staffInfo.getPosition());
    }

    public void setData(Long id, String name, String position){
        data.put("staffId",id);
        data.put("name",name);
        data.put("position",position);
    }

}
