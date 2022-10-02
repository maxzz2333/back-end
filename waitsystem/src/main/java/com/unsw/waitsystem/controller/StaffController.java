package com.unsw.waitsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.unsw.waitsystem.entity.Staff;
import com.unsw.waitsystem.service.StaffService;
import com.unsw.waitsystem.unit.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    @Qualifier("staffService")
    private StaffService staffService;

    /**
     *
     * @param staff
     * {
     *     "name":"",
     *     "password":"",
     *     "email":"",
     *     "position":"",
     * }
     *
     * @return Result
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Registration Success",
     * 	"data": {
     * 		"name": "zz",
     * 		"position": "wait staff",
     * 		"staffId": 1
     *        }
     * }
     */
    @PostMapping("/register")
    public Result staffRegister(@RequestBody Staff staff){
        Map<String, Object> registerResult = staffService.register(staff);
        if(registerResult.containsKey("staffId")){
            return Result.success("Registration Success").data(registerResult);
        }else {return Result.error(registerResult.get("errorMessage").toString());}
    }

    /**
     *
     * @param jsonObject
     *{
     *     "email":"",
     *     "password":"",
     * }
     * @return Result
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Login Success",
     * 	"data": {
     * 		"name": "zz",
     * 		"position": "wait staff",
     * 		"staffId": 1
     *        }
     * }
     */
    @GetMapping("/logIn")
    public Result staffLogIn(@RequestBody JSONObject jsonObject){
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        Map<String, Object> logInResult = staffService.logIn(email, password);
        if(logInResult.containsKey("staffId")){
            return Result.success("Login Success").data(logInResult);
        }else {return Result.error(logInResult.get("errorMessage").toString());}
    }

    /**
     *
     * @param jsonObject
     *{
     *     "staffId":"",
     *     "password":"",
     * }
     * @return Result
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Change Password Success",
     * 	"data": {
     * 		"name": "zz",
     * 		"position": "wait staff",
     * 		"staffId": 1
     *        }
     * }
     */
    @PutMapping("/changePassword")
    public Result staffChangePassword(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("staffId");
        String password = jsonObject.getString("password");
        Map<String, Object> logInResult = staffService.changePassword(id, password);
        if(logInResult.containsKey("staffId")){
            return Result.success("Change Password Success").data(logInResult);
        }else {return Result.error(logInResult.get("errorMessage").toString());}
    }

}
