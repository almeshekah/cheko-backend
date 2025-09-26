package com.cheko.app.dto;

import com.cheko.app.entities.CategoryEntity;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryEntityDto {

    private String categoryName;

    public CategoryEntityDto(CategoryEntity category) {
        this.categoryName = category.getName();
    }

    public CategoryEntityDto(HttpStatus badRequest, String message) {

    }

}
