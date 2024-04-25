package com.prasanna.Food.service;

import com.prasanna.Food.model.IngredientCategory;
import com.prasanna.Food.model.IngredientsItem;
import com.prasanna.Food.model.Restaurant;
import com.prasanna.Food.repository.IngredientCategoryRepository;
import com.prasanna.Food.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientsService {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        ingredientCategoryRepository.save(ingredientCategory);
        return null;
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {

        Optional<IngredientCategory> ingredientCategoryOpt = ingredientCategoryRepository.findById(id);
        if (ingredientCategoryOpt.isEmpty()) {
            throw new Exception("Ingredient Category not found");
        }
        return ingredientCategoryOpt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {

        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);

    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = findIngredientCategoryById(categoryId);

        IngredientsItem ingredientItem = new IngredientsItem();
        ingredientItem.setName(ingredientName);
        ingredientItem.setRestaurant(restaurant);
        ingredientItem.setCategory(ingredientCategory);

        IngredientsItem savedIngredientItem = ingredientItemRepository.save(ingredientItem);
        ingredientCategory.getIngredients().add(savedIngredientItem);

        return savedIngredientItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {


        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {

        Optional<IngredientsItem> ingredientItemOpt = ingredientItemRepository.findById(id);
        if (ingredientItemOpt.isEmpty()) {
            throw new Exception("Ingredient Item not found");
        }
        IngredientsItem ingredientItem = ingredientItemOpt.get();
        ingredientItem.setInStoke(!ingredientItem.isInStoke());
        return ingredientItemRepository.save(ingredientItem);
    }
}
