package com.prasanna.Food.service;

import com.prasanna.Food.dto.RestaurantDto;
import com.prasanna.Food.model.Address;
import com.prasanna.Food.model.Restaurant;
import com.prasanna.Food.model.User;
import com.prasanna.Food.repository.AddressRepository;
import com.prasanna.Food.repository.RestaurantRepository;
import com.prasanna.Food.repository.UserRepository;
import com.prasanna.Food.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningsHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType() != null)
                restaurant.setCuisineType(updatedRestaurant.getCuisineType());

        if(restaurant.getDescription() != null)
            restaurant.setDescription(updatedRestaurant.getDescription());

        if(restaurant.getName() !=null)
            restaurant.setName(updatedRestaurant.getName());


        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {


        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);


    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {

        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {

        Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
        if(opt.isEmpty())
            throw new Exception("Restaurant not found");
        return opt.get();
    }

    @Override
    public Restaurant findRestaurantByUserId(Long userId) throws Exception {

        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant== null)
                throw new Exception("Restaurant not found with ownerId: " + userId);
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourite(Long restaurantId, User user) throws Exception {


        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurant.getId());


        boolean isFavourite = false;
        List<RestaurantDto> favourites = user.getFavorites();
        for(RestaurantDto favourite : favourites) {

            if(favourite.getId().equals(restaurantId)){
                isFavourite = true;
                break;
            }
        }

        if(isFavourite)
            favourites.removeIf(favourite -> favourite.getId().equals(restaurantId));
        else
            favourites.add(dto);


        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {


        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
