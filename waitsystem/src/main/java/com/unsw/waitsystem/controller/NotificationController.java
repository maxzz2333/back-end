package com.unsw.waitsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.unsw.waitsystem.entity.Notification;
import com.unsw.waitsystem.service.NotificationService;
import com.unsw.waitsystem.unit.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    @Qualifier("notificationService")
    private NotificationService notificationService;

    /**
     *
     * @param jsonObject
     *{
     *     "orderId" : 1,
     *     "tableId" : 1,
     *     "time":"2015-12-20 10:01:00",
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Create Notification Successfully",
     * 	"data": {
     * 		"notification": {
     * 			"notificationId": 2,
     * 			"tableId": 1,
     * 			"orderId": 1,
     * 			"notificationState": 0,
     * 			"time": "2015-12-20 10:01:00",
     * 			"type": 0
     *                }
     *          }
     * }
     */
    @PostMapping("/createNotificationByCustomer")
    public Result createNotificationByCustomer(@RequestBody JSONObject jsonObject){
        int orderId = jsonObject.getInteger("orderId");
        Integer tableId = jsonObject.getInteger("tableId");
        String time = jsonObject.getString("time");
        Notification newNotification = notificationService.createNotification(new Notification(tableId,orderId,time,0));
        if(newNotification != null){
            return Result.success("Create Notification Successfully").data("notification",newNotification);
        }else {return Result.error("Create Notification Failed");}
    }

    /**
     *
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Create Notification Successfully",
     * 	"data": {
     * 		"notificationList": [
     *                        {
     * 				"notificationId": 2,
     * 				"tableId": 1,
     * 				"orderId": 1,
     * 				"notificationState": 0,
     * 				"time": "2015-12-20 10:01:00",
     * 				"type": 0
     *            },
     *            {
     * 				"notificationId": 3,
     * 				"tableId": 1,
     * 				"orderId": 2,
     * 				"notificationState": 0,
     * 				"time": "2015-12-20 10:01:00",
     * 				"type": 0
     *            }
     * 		]
     * 	}
     * }
     */
    @GetMapping("/getNoCompleteNotification")
    public Result getNoCompleteNotification(){
        List<Notification> notificationList = notificationService.getNoCompleteNotification();
        return Result.success("Create Notification Successfully").data("notificationList",notificationList);
    }

    /**
     *
     * @param jsonObject
     * {
     *     "notificationId":1,
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Complete Notification Successfully",
     * 	"data": {}
     * }
     */
    @PutMapping("/completeNotification")
    public Result completeNotification(@RequestBody JSONObject jsonObject){
        Integer notificationId = jsonObject.getInteger("notificationId");
        notificationService.completeNotification(notificationId);
        return Result.success("Complete Notification Successfully");
    }


}
