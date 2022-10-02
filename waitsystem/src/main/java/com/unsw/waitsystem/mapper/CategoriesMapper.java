package com.unsw.waitsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unsw.waitsystem.entity.Categories;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoriesMapper extends BaseMapper<Categories> {

    @Select("select * from categories")
    public List<Categories> getAllCategories();

    @Delete("delete from categories where categories_id = #{categoriesId}")
    public int deleteOneCategories(Long categoriesId);
}
