package com.prasanna.Food.service;

import com.prasanna.Food.dto.RestaurantDto;
import com.prasanna.Food.model.Restaurant;
import com.prasanna.Food.model.User;
import com.prasanna.Food.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {


    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long restaurantId) throws Exception;

    public Restaurant findRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavourite(Long restaurantId, User user)throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;


}
