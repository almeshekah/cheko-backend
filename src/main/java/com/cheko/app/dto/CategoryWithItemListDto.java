package com.cheko.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithItemListDto {

    private Long id;
    private String name;
    private List<MenuEntityDto> menuItems;

}