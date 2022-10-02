package com.unsw.waitsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TableName("menus")
public class Menu {
    @TableId(type = IdType.AUTO)
    private Long menuId;

    private String title;

    private String description ;

    private String ingredient ;

    private Integer categoriesId;

    private Double price;

    private Double cost;

    private String img;

    private int orderCount;

    private Double menuScore = 5.0; // 0 is no rating , 1 is bad , 2 is no bad , 3 is good, 4 is very good, 5 is great


    public Menu(){}
    public Menu(String title, String description, String ingredient, int categoriesId, Double price, Double cost, String img) {
        this.title = title;
        this.description = description;
        this.ingredient = ingredient;
        this.categoriesId = categoriesId;
        this.price = price;
        this.cost = cost;
        this.img = img;
    }


    public Long getMenuId() {
        return menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public int getOrderCount() {
        return orderCount;
    }

    public void addOrderCount() {
        this.orderCount += 1;
    }

    public void deleteOrderCount() {
        this.orderCount -= 1;
    }

    public Double getMenuScore() {
        return menuScore;
    }

    public void setMenuScore(Double menuScore) {
        this.menuScore = menuScore;
    }
}
