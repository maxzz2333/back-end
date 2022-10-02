package com.unsw.waitsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unsw.waitsystem.entity.Categories;
import com.unsw.waitsystem.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {
    @Select("select max(notification_id) from notification")
    public int getMaxId();

    @Select("select * from notification where notification_state = 0 order by time")
    public List<Notification> getNoCompleteNotification();
}
