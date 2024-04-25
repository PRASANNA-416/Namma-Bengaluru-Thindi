package com.prasanna.Food.controller;

import com.prasanna.Food.model.Food;
import com.prasanna.Food.model.Restaurant;
import com.prasanna.Food.model.User;
import com.prasanna.Food.request.CreateFoodRequest;
import com.prasanna.Food.service.FoodService;
import com.prasanna.Food.service.RestaurantService;
import com.prasanna.Food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);


        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);

    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @PathVariable Long restaurantId,
            @RequestParam(required = false) String food_category,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);


        List<Food> foods = foodService.getRestaurantsFoods(restaurantId, vegetarian, nonveg, food_category, seasonal);

        return new ResponseEntity<>(foods, HttpStatus.OK);

    }

}
