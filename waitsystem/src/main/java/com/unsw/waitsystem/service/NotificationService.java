package com.unsw.waitsystem.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.unsw.waitsystem.entity.Menu;
import com.unsw.waitsystem.entity.Notification;
import com.unsw.waitsystem.entity.Order;
import com.unsw.waitsystem.mapper.MenuMapper;
import com.unsw.waitsystem.mapper.NotificationMapper;
import com.unsw.waitsystem.mapper.OrderMapper;
import com.unsw.waitsystem.unit.StringUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    @Qualifier("notificationMapper")
    private NotificationMapper notificationMapper;

    @Autowired
    @Qualifier("orderMapper")
    private OrderMapper orderMapper;

    public Notification createNotification(Notification notification) {
        if(notificationMapper.insert(notification) == 0){
            return null;
        }
        int maxId = notificationMapper.getMaxId();
        return  notificationMapper.selectById(maxId);
    }

    public List<Notification> getNoCompleteNotification() {
       return notificationMapper.getNoCompleteNotification();
    }

    public void completeNotification(Integer notificationId) {
        Notification notification = notificationMapper.selectById(notificationId);
        notification.setNotificationState(1);
        UpdateWrapper<Notification> updateNotificationWrapper = new UpdateWrapper<>();
        updateNotificationWrapper.eq("notification_id",notificationId);
        notificationMapper.update(notification, updateNotificationWrapper);

        if(notification.getType() == 1){
            int orderId = notification.getOrderId();
            Order order = orderMapper.selectById(orderId);
            order.setOrderState(3);
            UpdateWrapper<Order> updateOrderWrapper = new UpdateWrapper<>();
            updateOrderWrapper.eq("order_id",orderId);
            orderMapper.update(order, updateOrderWrapper);
        }
    }


}
