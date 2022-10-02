package com.unsw.waitsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.unsw.waitsystem.entity.Order;
import com.unsw.waitsystem.entity.Table;
import com.unsw.waitsystem.service.OrderService;
import com.unsw.waitsystem.service.TableService;
import com.unsw.waitsystem.unit.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/table")
public class TableController {
    @Autowired
    @Qualifier("tableService")
    private TableService tableService;

    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    /**
     *
     * @return Result
     *{
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Success",
     * 	"data": {
     * 		"tableList": [
     *                        {
     * 				"tableId": 1,
     * 				"tableState": 1
     *            },
     *            {
     * 				"tableId": 2,
     * 				"tableState": 1
     *            },
     *            {
     * 				"tableId": 3,
     * 				"tableState": 0
     *            }
     * 		]
     * 	}
     * }
     *
     * tableState 0 is free, 1 is in-using
     */
    @GetMapping("/getAllTables")
    public Result getAllTables(){
        List<Table> allTables = tableService.getAllTables();
        return Result.success("Success").data("tableList",allTables);
    }

    /**
     *
     * @param jsonObject
     * {
     *     "tableId" : 1,
     *     "time" : "2015-12-20 10:01:00", //Note the format of the time
     * }
     * @return
     *{
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Select Success",
     * 	"data": {
     * 		"order": {
     * 			"orderId": 2,
     * 			"tableId": 1,
     * 			"orderState": 0,
     * 			"menusIds": "",
     * 			"menusNumber": 0,
     * 			"amount": 0,
     * 			"time": "2015-12-20 10:01:00",
     * 			"commentsIds": "",
     * 			"score": 0
     *                }
     *        }
     * }
     */
    @PutMapping("/selectTable")
    public Result selectTable(@RequestBody JSONObject jsonObject){
        int tableId = jsonObject.getInteger("tableId");
        String time = jsonObject.getString("time");
        tableService.selectTable(tableId);
        Order newOrder = orderService.createNewOrder(new Order(tableId, time));
        return Result.success("Select Success").data("order",newOrder);
    }

    /**
     *
     * @param jsonObject
     *  {
     *     "tableId" : 1,
     * }
     * @return Result
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Leave Success",
     * 	"data": {}
     * }
     */
    @PutMapping("/leaveTable")
    public Result leaveTable(@RequestBody JSONObject jsonObject){
        int tableId = jsonObject.getInteger("tableId");
        tableService.leaveTable(tableId);
        return Result.success("Leave Success");
    }
}
