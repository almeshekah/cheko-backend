package com.cheko.app.dto;

import java.math.BigDecimal;

import com.cheko.app.entities.MenuEntity;
import com.cheko.app.entities.CategoryEntity;
import com.cheko.app.entities.RestaurantEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MenuEntityDto {

    private Long itemId;

    private String itemName;

    private String itemDescription;

    private String itemImageUrl;

    private Integer itemCalories;

    private BigDecimal itemPrice;

    public MenuEntityDto(MenuEntity item) {
        this.itemId = item.getId();
        this.itemName = item.getName();
        this.itemDescription = item.getDescription();
        this.itemCalories = item.getCalories();
        this.itemImageUrl = item.getImageUrl();
        this.itemPrice = item.getPrice();

    }

}
