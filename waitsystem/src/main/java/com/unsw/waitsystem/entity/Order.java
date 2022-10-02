package com.unsw.waitsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;
@TableName("orders")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long orderId;
    private int tableId;

    private int orderState; // 0 = shopping 1 = preparing 2 = ready to serve 3 = served 4 = closed

    private String menusIds = "";

    private int menusNumber = 0;

    private Double amount = 0.0;
    private String time;

    private int orderScore = 0; // 0 is no rating , 1 is bad , 2 is no bad , 3 is good, 4 is very good, 5 is great


    public Order(){}

    public Order(int tableId, String time) {
        this.tableId = tableId;
        this.time = time;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }
    public Double getAmount() {
        return amount;
    }

    public void addAmount(Double price) {
        this.amount += price;
    }

    public void deleteAmount(Double price) {
        this.amount -= price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getScore() {
        return orderScore;
    }

    public void setScore(int score) {
        this.orderScore = score;
    }

    public String getMenusIds() {
        return menusIds;
    }

    public void setMenusIds(String menusIds) {
        this.menusIds = menusIds;
    }

    public int getMenusNumber() {
        return menusNumber;
    }

    public void addMenusNumber() {
        this.menusNumber += 1;
    }

    public void deleteMenusNumber() {
        this.menusNumber -= 1;
    }

}
