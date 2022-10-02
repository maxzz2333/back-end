package com.unsw.waitsystem.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.unsw.waitsystem.entity.Menu;
import com.unsw.waitsystem.entity.Notification;
import com.unsw.waitsystem.entity.Order;
import com.unsw.waitsystem.mapper.MenuMapper;
import com.unsw.waitsystem.mapper.NotificationMapper;
import com.unsw.waitsystem.mapper.OrderMapper;
import com.unsw.waitsystem.mapper.TableMapper;
import com.unsw.waitsystem.unit.StringUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    @Qualifier("orderMapper")
    private OrderMapper orderMapper;

    @Autowired
    @Qualifier("menuMapper")
    private MenuMapper menuMapper;

    @Autowired
    @Qualifier("tableMapper")
    private TableMapper tableMapper;

    @Autowired
    @Qualifier("notificationMapper")
    private NotificationMapper notificationMapper;

    public Order createNewOrder(Order order) {
        orderMapper.insert(order);
        int maxId = orderMapper.getMaxId();
        return orderMapper.getOrderInfoById(maxId);
    }

    public int addMenuItemToOrder(Integer orderId, Integer menuId) {
        Menu menu = menuMapper.selectById(menuId);
        Order order = orderMapper.selectById(orderId);

        //1. Get the price of menuId
        Double price = menu.getPrice();

        //2.Change the sales count of menu
        menu.addOrderCount();

        //3.Change the itemsid in the order
        order.setMenusIds(StringUnit.addId(order.getMenusIds(),menuId));

        //4.Change the amount in the order
        order.addMenusNumber();
        order.addAmount(price);

        //Update database

        UpdateWrapper<Menu> updateMenuWrapper = new UpdateWrapper<>();
        updateMenuWrapper.eq("menu_id",menuId);

        UpdateWrapper<Order> updateOrderWrapper = new UpdateWrapper<>();
        updateOrderWrapper.eq("order_id",orderId);

        int updateMenu = menuMapper.update(menu, updateMenuWrapper);
        int updateOrder = orderMapper.update(order, updateOrderWrapper);

        return updateMenu + updateOrder;
    }

    public int deleteMenuItemFromOrder(Integer orderId, Integer menuId) {
        Menu menu = menuMapper.selectById(menuId);
        Order order = orderMapper.selectById(orderId);

        //1. Get the price of menuId
        Double price = menu.getPrice();

        //2.Change the sales count of menu
        menu.deleteOrderCount();

        //3.Change the itemsid in the order
        order.setMenusIds(StringUnit.deleteId(order.getMenusIds(),menuId));
        List<Integer> menusId = StringUnit.getIdList(order.getMenusIds());
        if(menusId.size() == 0){order.setMenusIds("");}

        //4.Change the amount in the order
        order.deleteMenusNumber();
        order.deleteAmount(price);

        //Update database

        UpdateWrapper<Menu> updateMenuWrapper = new UpdateWrapper<>();
        updateMenuWrapper.eq("menu_id",menuId);

        UpdateWrapper<Order> updateOrderWrapper = new UpdateWrapper<>();
        updateOrderWrapper.eq("order_id",orderId);

        int updateMenu = menuMapper.update(menu, updateMenuWrapper);
        int updateOrder = orderMapper.update(order, updateOrderWrapper);

        return updateMenu + updateOrder;
    }


    public HashMap<String, Object> checkOrder(Integer orderId) {
        HashMap<String, Object> data = new HashMap<String, Object>(){{
            put("order", null);
            put("menuList", null);
        }};

        Order order = orderMapper.selectById(orderId);
        data.put("order",order);
        List<Integer> menusId = StringUnit.getIdList(order.getMenusIds());
        if(menusId.size()!= 0){
            List<Menu> menus = menuMapper.selectBatchIds(menusId);
            data.put("menuList",menus);
        }
        return data;
    }

    public Object takeOrder(Integer orderId) {
        changeOrderState(orderId,1);
        return orderMapper.selectById(orderId);
    }

    public List<Order> getOrderToKitchen() {
        return orderMapper.getOrderToKitchen();
    }

    public int getReadyFromKitchen(int orderId, String time) {
        changeOrderState(orderId,2);
        int insert = notificationMapper.insert(new Notification(changeOrderState(orderId, 2), orderId, time, 1));
        return insert;
    }

    public int changeOrderState(int orderId,int state){
        Order order = orderMapper.selectById(orderId);
        order.setOrderState(state);
        UpdateWrapper<Order> updateOrderWrapper = new UpdateWrapper<>();
        updateOrderWrapper.eq("order_id",orderId);
        orderMapper.update(order, updateOrderWrapper);
        return order.getTableId();
    }

    public Order sendOrderToCustomer(int orderId) {
        changeOrderState(orderId,3);
        return orderMapper.selectById(orderId);
    }

    public Object checkBill(int orderId) {
        tableMapper.leaveTable(changeOrderState(orderId,4));
        return orderMapper.selectById(orderId);
    }
}
