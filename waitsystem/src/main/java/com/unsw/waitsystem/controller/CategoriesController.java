package com.unsw.waitsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.unsw.waitsystem.entity.Categories;
import com.unsw.waitsystem.entity.Staff;
import com.unsw.waitsystem.service.CategoriesService;
import com.unsw.waitsystem.service.StaffService;
import com.unsw.waitsystem.unit.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    @Qualifier("categoriesService")
    private CategoriesService categoriesService;

    /**
     *
     * @param jsonObject
     * {
     *     "title": "Salass",
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Added Successfully",
     * 	"data": {}
     * }
     */
    @PostMapping("/addNewCategories")
    public Result addNewCategories(@RequestBody JSONObject jsonObject){
        if(categoriesService.addNewCategories(new Categories(jsonObject.getString("title"))) > 0){
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
     * 		"categoriesList": [
     *                        {
     * 				"categoriesId": 1,
     * 				"sortId": 0,
     * 				"title": "Drinks"
     *            },
     *            {
     * 				"categoriesId": 2,
     * 				"sortId": 0,
     * 				"title": "Salas"
     *            }
     * 		]
     * 	}
     * }
     */
    @GetMapping("/getAllCategories")
    public Result getAllCategories(){
        List<Categories> allCategories = categoriesService.getAllCategories();
        return Result.success("Success").data("categoriesList",allCategories);
    }

    /**
     *
     * @param jsonObject
     * {
     *     "categorisesId": "1",
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Delete Successfully",
     * 	"data": {}
     * }
     */
    @PutMapping("/deleteOneCategories")
    public Result deleteOneCategories(@RequestBody JSONObject jsonObject){
        if(categoriesService.deleteOneCategories(jsonObject.getLong("categorisesId")) > 0){
            return Result.success("Delete Successfully");
        }else {return Result.error("Delete Failed");}
    }

    /**
     *
     * @param jsonObject
     * {
     *     "categoriesId":5,
     *     "title": "sea food",
     * }
     * @return
     * {
     * 	"success": true,
     * 	"stateCode": 200,
     * 	"message": "Change Successfully",
     * 	"data": {}
     * }
     */
    @PutMapping("/changeOneCategories")
    public Result changeOneCategories(@RequestBody JSONObject jsonObject){
        Long categorisesId = jsonObject.getLong("categoriesId");
        String title = jsonObject.getString("title");
        if(categoriesService.changeOneCategories(categorisesId,title) > 0){
            return Result.success("Change Successfully");
        }else {return Result.error("Change Failed");}
    }

}
