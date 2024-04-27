package com.prasanna.Food.repository;

import com.prasanna.Food.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r from Restaurant r WHERE lower(r.name) LIKE lower(concat('%',?1,'%')) OR lower(r.cuisineType) LIKE lower(concat('%',?1,'%'))")
    public abstract List<Restaurant> findBySearchQuery(String keyword);
    Restaurant findByOwnerId(Long userId);

}
