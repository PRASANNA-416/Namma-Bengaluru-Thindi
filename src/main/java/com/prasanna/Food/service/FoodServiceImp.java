package com.prasanna.Food.service;

import com.prasanna.Food.model.Category;
import com.prasanna.Food.model.Food;
import com.prasanna.Food.model.Restaurant;
import com.prasanna.Food.repository.FoodRepository;
import com.prasanna.Food.request.CreateFoodRequest;
import org.apache.catalina.startup.ExpandWar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp  implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setPrice(req.getPrice());
        food.setName(req.getName());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        Food savedFood = foodRepository.save(food);

        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);


    }

    @Override
    public List<Food> getRestaurantsFoods(Long restaurantId, boolean isVegetarian, boolean isNonVeg, String foodCategory, boolean isSeasonal) throws Exception {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVegetarian)
            foods = filterByVegetarian(foods, isVegetarian);
        if(isNonVeg)
            foods = filterByNonVegetarian(foods, isNonVeg);

        if(isSeasonal)
            foods = filterBySeasonal(foods, isSeasonal);

        if(foodCategory != null && !foodCategory.isEmpty())
            foods = filterByCategory(foods, foodCategory);

        return foods;

    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {

        return foods.stream().filter(food -> {
            if(food.getFoodCategory() != null){
                return food.getFoodCategory().getName().equals(foodCategory);
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {

        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {

        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {

        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if(optionalFood.isEmpty()) throw new Exception("Food not found");

        return optionalFood.get();
    }

    @Override
    public Food updateAvailability(Long foodId) throws Exception {


        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
