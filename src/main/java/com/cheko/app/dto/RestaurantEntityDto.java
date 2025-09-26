package com.cheko.app.dto;

import org.springframework.http.HttpStatus;

import com.cheko.app.entities.RestaurantEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEntityDto {

    private String restaurantName;

    private double restaurantLet;

    private double restaurantLng;

    public RestaurantEntityDto(RestaurantEntity restaurant) {
        this.restaurantName = restaurant.getName();
        this.restaurantLet = restaurant.getLet();
        this.restaurantLng = restaurant.getLng();
    }

    public RestaurantEntityDto(HttpStatus badRequest, String message) {

    }

}
