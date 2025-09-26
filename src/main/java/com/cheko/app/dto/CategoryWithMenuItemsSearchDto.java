package com.cheko.app.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryWithMenuItemsSearchDto {

    private Long id;
    private String name;
    private List<MenuEntityDto> menuItems;

    public CategoryWithMenuItemsSearchDto(Long id, String name, List<MenuEntityDto> menuItems) {
        this.id = id;
        this.name = name;
        this.menuItems = menuItems;
    }

}
