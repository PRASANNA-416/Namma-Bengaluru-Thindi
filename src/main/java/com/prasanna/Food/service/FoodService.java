package com.prasanna.Food.service;

import com.prasanna.Food.model.Category;
import com.prasanna.Food.model.Food;
import com.prasanna.Food.model.Restaurant;
import com.prasanna.Food.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFoods(Long restaurantId, boolean isVegetarian,boolean isNonVeg, String foodCategory, boolean isSeasonal) throws Exception;


    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailability(Long foodId) throws Exception;

}
