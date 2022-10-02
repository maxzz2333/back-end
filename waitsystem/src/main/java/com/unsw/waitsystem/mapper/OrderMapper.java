package com.unsw.waitsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unsw.waitsystem.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select * from orders where order_id = #{orderId}")
    public Order getOrderInfoById(int orderId);

    @Select("select max(order_id) from orders")
    public int getMaxId();

    @Update("update menus set items_id = #{itemsId}  amount = #{amount} where order_id = #{orderId}")
    public void addMenuItemToOrder(int orderId, String itemsId, Double amount);
    @Select("select * from orders where order_state = 1 order by time")
    public List<Order> getOrderToKitchen();
}
