package com.cheko.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheko.app.dto.RestaurantEntityDto;
import com.cheko.app.entities.RestaurantEntity;
import com.cheko.app.repositories.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantEntityDto> getAllRestaurants() {
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(RestaurantEntityDto::new).collect(Collectors.toList());
    }
}
