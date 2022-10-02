package com.unsw.waitsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.unsw.waitsystem.entity.Categories;
import com.unsw.waitsystem.entity.Menu;
import com.unsw.waitsystem.service.CategoriesService;
import com.unsw.waitsystem.service.MenuService;
import com.unsw.waitsystem.unit.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    @Qualifier("menuService")
    private MenuService menuService;

    /**
     *
     * @param menu
     * {
     *     "title": "doufu3",
     *     "description": "good",
     *     "ingredient" : "doufu",
     *     "categoriesId" :2,
     *     "price" :100,
     *     "cost" : 10,
     *     "img":"zzzzzzzz",
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Added Successfully",
     * 	"data": {}
     * }
     */
    @PostMapping("/addNewMenuitem")
    public Result addNewMenuitem(@RequestBody Menu menu){
        if(menuService.addNewMenuitem(menu) > 0){
            return Result.success("Added Successfully");
        }else {return Result.error("Added Failed");}
    }

    /**
     *
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Success",
     * 	"data": {
     * 		"menuList": [
     *                        {
     * 				"menuId": 1,
     * 				"title": "doufu1",
     * 				"description": "good",
     * 				"ingredient": "doufu",
     * 				"categoriesId": 1,
     * 				"price": 100,
     * 				"cost": 10,
     * 				"img": "zzzzzzzz",
     * 				"orderCount": 0,
     * 				"menuScore": 5
     *            },
     *            {
     * 				"menuId": 2,
     * 				"title": "doufu2",
     * 				"description": "good",
     * 				"ingredient": "doufu",
     * 				"categoriesId": 2,
     * 				"price": 100,
     * 				"cost": 10,
     * 				"img": "zzzzzzzz",
     * 				"orderCount": 0,
     * 				"menuScore": 5
     *            }
     * 		]
     * 	}
     * }
     */
    @GetMapping("/getAllMenu")
    public Result getAllMenu(){
        List<Menu> allMenu = menuService.getAllMenu();
        return Result.success("Success").data("menuList",allMenu);
    }

    /**
     *
     * @param jsonObject
     * {
     *     "categoriesId": 1,
     *     "sortType": 0
     *     0 is NO SORT
     *     1 IS PriceDesc
     *     2 IS PriceAsc
     *     3 IS OrderCountDesc
     *     4 IS OrderCountAsc
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Success",
     * 	"data": {
     * 		"menuList": [
     *                        {
     * 				"menuId": 1,
     * 				"title": "doufu1",
     * 				"description": "good",
     * 				"ingredient": "doufu",
     * 				"categoriesId": 1,
     * 				"price": 100,
     * 				"cost": 10,
     * 				"img": "zzzzzzzz",
     * 				"orderCount": 0
     *            }
     * 		]
     * 	}
     * }
     */
    @GetMapping("/getAllMenuByCategoriesBySortType")
    public Result getAllMenuByCategoriesBySortType(@RequestBody JSONObject jsonObject){
        List<Menu> allMenuByCategories = new ArrayList<>();
        Long categoriesId = jsonObject.getLong("categoriesId");
        Integer sortType = jsonObject.getInteger("sortType");
        switch (sortType){
            case 0:
                allMenuByCategories = menuService.getAllMenuByCategories(categoriesId);
                break;
            case 1:
                allMenuByCategories = menuService.getAllMenuByCategoriesOrderByPriceDesc(categoriesId);
                break;
            case 2:
                allMenuByCategories = menuService.getAllMenuByCategoriesOrderByPriceAsc(categoriesId);
                break;
            case 3:
                allMenuByCategories = menuService.getAllMenuByCategoriesOrderCountDesc(categoriesId);
                break;
            case 4:
                allMenuByCategories = menuService.getAllMenuByCategoriesOrderByOrderCountAsc(categoriesId);
                break;
        }

        return Result.success("Success").data("menuList",allMenuByCategories);
    }

    /**
     *
     * @param jsonObject
     * {
     *     "categoriesId": 1,
     *     "sortType": 0
     *     0 is NO SORT
     *     1 IS PriceDesc
     *     2 IS PriceAsc
     *     3 IS OrderCountDesc
     *     4 IS OrderCountAsc
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Success",
     * 	"data": {
     * 		"menuList": [
     *                        {
     * 				"menuId": 1,
     * 				"title": "doufu1",
     * 				"description": "good",
     * 				"ingredient": "doufu",
     * 				"categoriesId": 1,
     * 				"price": 100,
     * 				"cost": 10,
     * 				"img": "zzzzzzzz",
     * 				"orderCount": 0
     *            }
     * 		]
     * 	}
     * }
     */
    @GetMapping("/getAllMenuBySortType")
    public Result getAllMenuBySortType(@RequestBody JSONObject jsonObject){
        List<Menu> allMenu = new ArrayList<>();
        Integer sortType = jsonObject.getInteger("sortType");
        switch (sortType){
            case 0:
                allMenu = menuService.getAllMenu();
                break;
            case 1:
                allMenu = menuService.getAllMenuOrderByPriceDesc();
                break;
            case 2:
                allMenu = menuService.getAllMenuOrderByPriceAsc();
                break;
            case 3:
                allMenu = menuService.getAllMenuByOrderCountDesc();
                break;
            case 4:
                allMenu = menuService.getAllMenuOrderByOrderCountAsc();
                break;
        }

        return Result.success("Success").data("menuList",allMenu);
    }


    /**
     *
     * @param jsonObject
     * {
     *     "menuId": 1,
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Delete Successfully",
     * 	"data": {}
     * }
     */
    @PutMapping("/deleteOneMenuitem")
    public Result deleteOneMenuitem(@RequestBody JSONObject jsonObject){
        if(menuService.deleteOneMenuitem(jsonObject.getLong("menuId")) > 0){
            return Result.success("Delete Successfully");
        }else {return Result.error("Delete Failed");}
    }

    /**
     *
     * @param jsonObject
     *{
     *     "menuId":1,
     *     "title": "sea food"
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Delete Successfully",
     * 	"data": {
     * 		"menu": {
     * 			"menuId": 1,
     * 			"title": "sea food",
     * 			"description": "good",
     * 			"ingredient": "doufu",
     * 			"categoriesId": 1,
     * 			"price": 100,
     * 			"cost": 10,
     * 			"img": "zzzzzzzz",
     * 			"orderCount": 2,
     * 			"menuScore": 5
     *                }   }
     * }
     */
    @PutMapping("/changeMenuitemTitle")
    public Result changeMenuitemTitle(@RequestBody JSONObject jsonObject){
        Integer menuId = jsonObject.getInteger("menuId");
        String title = jsonObject.getString("title");
        return Result.success("Change Successfully").data("menu",menuService.changeMenuitemTitle(menuId,title));
    }

    /**
     *
     * @param jsonObject
     * {
     *     "menuId":1,
     *     "description":"so good"
     * }
     * @return
     */
    @PutMapping("/changeMenuitemDescription")
    public Result changeMenuitemDescription(@RequestBody JSONObject jsonObject){
        Integer menuId = jsonObject.getInteger("menuId");
        String description = jsonObject.getString("description");
        return Result.success("Change Successfully").data("menu",menuService.changeMenuitemDescription(menuId,description));
    }

    /**
     *
     * @param jsonObject
     * {
     *     "menuId":1,
     *     "ingredient":"sss"
     * }
     * @return
     */
    @PutMapping("/changeMenuitemIngredient")
    public Result changeMenuitemIngredient(@RequestBody JSONObject jsonObject){
        Integer menuId = jsonObject.getInteger("menuId");
        String ingredient = jsonObject.getString("ingredient");
        return Result.success("Change Successfully").data("menu",menuService.changeMenuitemIngredient(menuId,ingredient));
    }

    /**
     *
     * @param jsonObject
     * {
     *     "menuId":1,
     *     "price": 200
     * }
     * @return
     */
    @PutMapping("/changeMenuitemPrice")
    public Result changeMenuitemPrice(@RequestBody JSONObject jsonObject){
        Integer menuId = jsonObject.getInteger("menuId");
        Double price = jsonObject.getDouble("price");
        return Result.success("Change Successfully").data("menu",menuService.changeMenuitemPrice(menuId,price));
    }
    /**
     *
     * @param jsonObject
     * {
     *     "menuId":1,
     *     "cost":100
     * }
     * @return
     */
    @PutMapping("/changeMenuitemCost")
    public Result changeMenuitemCost(@RequestBody JSONObject jsonObject){
        Integer menuId = jsonObject.getInteger("menuId");
        Double cost = jsonObject.getDouble("cost");
        return Result.success("Change Successfully").data("menu",menuService.changeMenuitemCost(menuId,cost));
    }
    /**
     *
     * @param jsonObject
     * {
     *     "menuId":1,
     *     "img":"so good"
     * }
     * @return
     */
    @PutMapping("/changeMenuitemImg")
    public Result changeMenuitemImg(@RequestBody JSONObject jsonObject){
        Integer menuId = jsonObject.getInteger("menuId");
        String img = jsonObject.getString("img");
        return Result.success("Change Successfully").data("menu",menuService.changeMenuitemImg(menuId,img));
    }
    /**
     *
     * @param jsonObject
     * {
     *     "menuId":1,
     *     "categoriesId":2
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Change Successfully",
     * 	"data": {
     * 		"menu": {
     * 			"menuId": 1,
     * 			"title": "sea food",
     * 			"description": "so good",
     * 			"ingredient": "sea food",
     * 			"categoriesId": 2,
     * 			"price": 200,
     * 			"cost": 100,
     * 			"img": "wwwwwwwww",
     * 			"orderCount": 2,
     * 			"menuScore": 5
     *              }
     *          }
     * }
     */
    @PutMapping("/changeMenuitemCategoriesId")
    public Result changeMenuitemCategoriesId(@RequestBody JSONObject jsonObject){
        Integer menuId = jsonObject.getInteger("menuId");
        Integer categoriesId = jsonObject.getInteger("categoriesId");
        return Result.success("Change Successfully").data("menu",menuService.changeMenuitemCategoriesId(menuId,categoriesId));
    }

}
