package com.prasanna.Food.service;

import com.prasanna.Food.model.Category;
import com.prasanna.Food.model.Food;
import com.prasanna.Food.model.Restaurant;
import com.prasanna.Food.request.CreateFoodRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImp  implements FoodService {
    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        return null;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

    }

    @Override
    public List<Food> getRestaurantsFoods(Long restaurantId, boolean isVegetarian, boolean isNonVeg, String foodCategory, boolean isSeasonal) throws Exception {
        return List.of();
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        return null;
    }

    @Override
    public Food updateAvailability(Long foodId) throws Exception {
        return null;
    }
}
