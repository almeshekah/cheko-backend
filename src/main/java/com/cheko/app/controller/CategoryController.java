package com.cheko.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cheko.app.dto.CategoryEntityDto;
import com.cheko.app.services.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<CategoryEntityDto> getAllCategories() {
        return service.retrieveAllCategories();
    }

}
