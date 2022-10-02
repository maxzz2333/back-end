package com.unsw.waitsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unsw.waitsystem.entity.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Delete("delete from menus where menu_id = #{menuId}")
    public int deleteOneMenuitem(Long menuId);

    @Select("select * from menus where categories_id = #{categoriesId}")
    List<Menu> getAllMenuByCategories(Long categoriesId);

    @Update("update menus set order_count = #{orderCount} where menu_id = #{menuId}")
    void addMenuItemToOrder(int menuId, int orderCount);
    @Select("select * from menus where categories_id = #{categoriesId} order by price desc")
    List<Menu> getAllMenuByCategoriesOrderByPriceDesc(Long categoriesId);
    @Select("select * from menus where categories_id = #{categoriesId} order by price")
    List<Menu> getAllMenuByCategoriesOrderByPriceAsc(Long categoriesId);
    @Select("select * from menus where categories_id = #{categoriesId} order by order_count desc")
    List<Menu> getAllMenuByCategoriesOrderCountDesc(Long categoriesId);
    @Select("select * from menus where categories_id = #{categoriesId} order by order_count")
    List<Menu> getAllMenuByCategoriesOrderByOrderCountAsc(Long categoriesId);

    @Select("select * from menus")
    public List<Menu> getAllMenu();

    @Select("select * from menus order by price desc")
    public List<Menu> getAllMenuOrderByPriceDesc();
    @Select("select * from menus order by price")
    List<Menu> getAllMenuOrderByPriceAsc();
    @Select("select * from menus order by order_count desc")
    List<Menu> getAllMenuByOrderCountDesc();
    @Select("select * from menus order by order_count")
    List<Menu> getAllMenuOrderByOrderCountAsc();
}
