package com.unsw.waitsystem.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.unsw.waitsystem.entity.Menu;
import com.unsw.waitsystem.entity.Order;
import com.unsw.waitsystem.mapper.CategoriesMapper;
import com.unsw.waitsystem.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuService {

    @Autowired
    @Qualifier("menuMapper")
    private MenuMapper menuMapper;

    public int addNewMenuitem(Menu menu) {
        return menuMapper.insert(menu);
    }

    public int deleteOneMenuitem(Long menuId) {
        return menuMapper.deleteOneMenuitem(menuId);
    }

    public List<Menu> getAllMenu() {
       return menuMapper.getAllMenu();
    }


    public Menu changeMenuitemTitle(Integer menuId, String title) {
        Menu menu = menuMapper.selectById(menuId);
        menu.setTitle(title);
        changeMenuitem(menuId,menu);
        return menuMapper.selectById(menuId);
    }

    public Menu changeMenuitemDescription(Integer menuId, String description) {
        Menu menu = menuMapper.selectById(menuId);
        menu.setDescription(description);
        changeMenuitem(menuId,menu);
        return menuMapper.selectById(menuId);
    }

    public Menu changeMenuitemIngredient(Integer menuId, String ingredient) {
        Menu menu = menuMapper.selectById(menuId);
        menu.setIngredient(ingredient);
        changeMenuitem(menuId,menu);
        return menuMapper.selectById(menuId);
    }

    public Menu changeMenuitemPrice(Integer menuId, Double price) {
        Menu menu = menuMapper.selectById(menuId);
        menu.setPrice(price);
        changeMenuitem(menuId,menu);
        return menuMapper.selectById(menuId);
    }

    public Menu changeMenuitemCost(Integer menuId, Double cost) {
        Menu menu = menuMapper.selectById(menuId);
        menu.setCost(cost);
        changeMenuitem(menuId,menu);
        return menuMapper.selectById(menuId);
    }

    public Menu changeMenuitemImg(Integer menuId, String img) {
        Menu menu = menuMapper.selectById(menuId);
        menu.setImg(img);
        changeMenuitem(menuId,menu);
        return menuMapper.selectById(menuId);
    }

    public Menu changeMenuitemCategoriesId(Integer menuId, Integer categoriesId) {
        Menu menu = menuMapper.selectById(menuId);
        menu.setCategoriesId(categoriesId);
        changeMenuitem(menuId,menu);
        return menuMapper.selectById(menuId);
    }

    private void changeMenuitem(Integer menuId,Menu menu){
        UpdateWrapper<Menu> updateMenuWrapper = new UpdateWrapper<>();
        updateMenuWrapper.eq("menu_id",menuId);
        menuMapper.update(menu, updateMenuWrapper);
    }

    public List<Menu> getAllMenuByCategories(Long categoriesId) {
        return menuMapper.getAllMenuByCategories(categoriesId);
    }

    public List<Menu> getAllMenuByCategoriesOrderByPriceDesc(Long categoriesId) {
        return menuMapper.getAllMenuByCategoriesOrderByPriceDesc(categoriesId);
    }

    public List<Menu> getAllMenuByCategoriesOrderByPriceAsc(Long categoriesId) {
        return menuMapper.getAllMenuByCategoriesOrderByPriceAsc(categoriesId);
    }

    public List<Menu> getAllMenuByCategoriesOrderCountDesc(Long categoriesId) {
        return menuMapper.getAllMenuByCategoriesOrderCountDesc(categoriesId);
    }

    public List<Menu> getAllMenuByCategoriesOrderByOrderCountAsc(Long categoriesId) {
        return menuMapper.getAllMenuByCategoriesOrderByOrderCountAsc(categoriesId);
    }

    public List<Menu> getAllMenuOrderByPriceDesc() {
        return menuMapper.getAllMenuOrderByPriceDesc();
    }

    public List<Menu> getAllMenuOrderByPriceAsc() {
        return menuMapper.getAllMenuOrderByPriceAsc();
    }

    public List<Menu> getAllMenuByOrderCountDesc() {
        return menuMapper.getAllMenuByOrderCountDesc();
    }

    public List<Menu> getAllMenuOrderByOrderCountAsc() {
        return menuMapper. getAllMenuOrderByOrderCountAsc();
    }
}
