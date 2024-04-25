package com.prasanna.Food.repository;

import com.prasanna.Food.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {


    List<IngredientsItem> findByRestaurantId(Long restaurantId);

}
