package com.unsw.waitsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.unsw.waitsystem.entity.Menu;
import com.unsw.waitsystem.entity.Order;
import com.unsw.waitsystem.service.MenuService;
import com.unsw.waitsystem.service.OrderService;
import com.unsw.waitsystem.unit.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    /**
     * When table is selected a new blank order is automatically generated
     * @param order
     * @return
     */
    @PostMapping("/createNewOrder")
    public Result createNewOrder(@RequestBody Order order){
        Order newOrder = orderService.createNewOrder(order);
        if(newOrder != null){
            return Result.success("CreateNewOrder Successfully").data("order",newOrder);
        }else {return Result.error("CreateNewOrder Failed");}
    }

    /**
     *
     * @param jsonObject
     *{
     *     "orderId":1,
     *     "menuId":1,
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Add item Successfully",
     * 	"data": {}
     * }
     */
    @PutMapping("/addMenuItemToOrder")
    public Result addMenuItemToOrder(@RequestBody JSONObject jsonObject){
        int orderId = jsonObject.getInteger("orderId");
        int menuId = jsonObject.getInteger("menuId");
        int result = orderService.addMenuItemToOrder(orderId,menuId);
        if(result == 2){
            return Result.success("Add Item Successfully");
        }
        return Result.error("Add Item Failure");
    }
    /**
     *
     * @param jsonObject
     *{
     *     "orderId":1,
     *     "menuId":1,
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Delete item Successfully"
     * 	"data": {}
     * }
     */
    @DeleteMapping("/deleteMenuItemFromOrder")
    public Result deleteMenuItemFromOrder(@RequestBody JSONObject jsonObject){
        Integer orderId = jsonObject.getInteger("orderId");
        Integer menuId = jsonObject.getInteger("menuId");
        int result = orderService.deleteMenuItemFromOrder(orderId, menuId);
        if(result == 2){
            return Result.success("Delete Item Successfully");
        }
        return Result.error("Delete Item Failure");
    }

    /**
     *
     * @param jsonObject
     * {
     *     "orderId":1,
     * }
     * @return Result
     *{
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Check Order Successfully",
     * 	"data": {
     * 		"menuList": [
     *                        {
     * 				"menuId": 1,
     * 				"title": "doufu1",
     * 				"description": "good",
     * 				"ingredient": "doufu",
     * 				"categoriesId": 1,
     * 				"price": 100,
     * 				"cost": 10,
     * 				"img": "zzzzzzzz",
     * 				"orderCount": 0,
     * 				"menuScore": 5
     *            }
     * 		],
     * 		"order": {
     * 			"orderId": 1,
     * 			"tableId": 1,
     * 			"orderState": 0,
     * 			"menusIds": ",1",
     * 			"menusNumber": 1,
     * 			"amount": 100,
     * 			"time": "2015-12-20 10:01:00",
     * 			"score": 0
     * 		}
     *    }
     * }
     */
    @GetMapping("/checkOrder")
    public Result checkOrder(@RequestBody JSONObject jsonObject){
        int orderId = jsonObject.getInteger("orderId");
        return Result.success("Check Order Successfully").data(orderService.checkOrder(orderId));
    }

    /**
     * Change orderState  = 1
     *
     * @param jsonObject
     * {
     *     "orderId":1,
     * }
     * @return Result
     *{
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Check Order Successfully",
     * 	"data": {
     * 		"order": {
     * 			"orderId": 1,
     * 			"tableId": 1,
     * 			"orderState": 1,
     * 			"menusIds": ",1",
     * 			"menusNumber": 1,
     * 			"amount": 100,
     * 			"time": "2015-12-20 10:01:00",
     * 			"score": 0
     * 		}
     *    }
     * }
     */
    @PutMapping("/takeOrder")
    public Result takeOrder(@RequestBody JSONObject jsonObject){
        int orderId = jsonObject.getInteger("orderId");
        return Result.success("Take Order Successfully").data("order",orderService.takeOrder(orderId));
    }

    /**
     *
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Successfully",
     * 	"data": {
     * 		"orderList": [
     *                        {
     * 				"orderId": 1,
     * 				"tableId": 1,
     * 				"orderState": 1,
     * 				"menusIds": ",1",
     * 				"menusNumber": 1,
     * 				"amount": 100,
     * 				"time": "2015-12-20 10:01:00",
     * 				"score": 0
     *            }
     * 		]
     * 	}
     * }
     */
    @GetMapping("/getOrderToKitchen")
    public Result getOrderToKitchen(){
        return Result.success("Successfully").data("orderList",orderService.getOrderToKitchen());
    }

    /**
     *
     * @param jsonObject
     *{
     *     "orderId":1
     *     "time": "2015-12-20 10:01:00"
     *}
     * @return Result
     */
    @PutMapping("/getReadyFromKitchen")
    public Result getReadyFromKitchen(@RequestBody JSONObject jsonObject){
        int orderId = jsonObject.getInteger("orderId");
        String time = jsonObject.getString("time");
        int readyFromKitchen = orderService.getReadyFromKitchen(orderId, time);
        if(readyFromKitchen == 1){
            return Result.success("Successfully");
        }else {return Result.error("Failure");}
    }

    /**
     *
     * @param jsonObject
     *{
     *     "orderId":1
     *
     *}
     * @return Result
     *{
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Successfully",
     * 	"data": {
     * 		"order": {
     * 			"orderId": 1,
     * 			"tableId": 1,
     * 			"orderState": 1,
     * 			"menusIds": ",1",
     * 			"menusNumber": 1,
     * 			"amount": 100,
     * 			"time": "2015-12-20 10:01:00",
     * 			"score": 0
     * 		}
     *    }
     * }
     */
    @PutMapping("/sendOrderToCustomer")
    public Result sendOrderToCustomer(@RequestBody JSONObject jsonObject){
        int orderId = jsonObject.getInteger("orderId");
        return Result.success("Successfully").data("order",orderService.sendOrderToCustomer(orderId));
    }

    /**
     *
     * @param jsonObject
     *{
     *     "orderId":1
     *
     *}
     * @return Result
     *{
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Check Successfully",
     * 	"data": {
     * 		"order": {
     * 			"orderId": 1,
     * 			"tableId": 1,
     * 			"orderState": 1,
     * 			"menusIds": ",1",
     * 			"menusNumber": 1,
     * 			"amount": 100,
     * 			"time": "2015-12-20 10:01:00",
     * 			"score": 0
     * 		}
     *    }
     * }
     */
    @PutMapping("/checkBill")
    public Result checkBill(@RequestBody JSONObject jsonObject){
        int orderId = jsonObject.getInteger("orderId");
        return Result.success("Check Successfully").data("order",orderService.checkBill(orderId));
    }

}
