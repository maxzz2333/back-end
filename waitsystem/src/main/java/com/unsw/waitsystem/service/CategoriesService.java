package com.unsw.waitsystem.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.unsw.waitsystem.entity.Categories;
import com.unsw.waitsystem.entity.Order;
import com.unsw.waitsystem.mapper.CategoriesMapper;
import com.unsw.waitsystem.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoriesService")
public class CategoriesService {

    @Autowired
    @Qualifier("categoriesMapper")
    private CategoriesMapper categoriesMapper;

    public int addNewCategories(Categories categories){
        return categoriesMapper.insert(categories);
    }

    public int deleteOneCategories(Long categoriesId){
        return categoriesMapper.deleteOneCategories(categoriesId);
    }

    public List<Categories> getAllCategories(){
        return categoriesMapper.getAllCategories();
    }

    public int changeOneCategories(Long categoriesId, String title) {
        Categories categories = categoriesMapper.selectById(categoriesId);
        categories.setTitle(title);
        UpdateWrapper<Categories> updateCategoriesWrapper = new UpdateWrapper<>();
        updateCategoriesWrapper.eq("categories_id",categoriesId);
        int update = categoriesMapper.update(categories, updateCategoriesWrapper);
        return update;
    }
}
