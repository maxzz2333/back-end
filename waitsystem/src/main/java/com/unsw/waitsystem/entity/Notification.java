package com.unsw.waitsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("notification")
public class Notification {
    @TableId(type = IdType.AUTO)
    private Long notificationId;
    private int tableId;
    private int orderId;
    private int notificationState; // 0 is not complete  1 is completed
    private String time;
    private int type ; // 0 is by customer   1 is by kitchen staff

    public Notification(int tableId, int orderId, String time, int type) {
        this.tableId = tableId;
        this.orderId = orderId;
        this.time = time;
        this.type = type;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getNotificationState() {
        return notificationState;
    }

    public void setNotificationState(int notificationState) {
        this.notificationState = notificationState;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
