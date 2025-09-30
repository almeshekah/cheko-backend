package com.cheko.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheko.app.dto.CategoryWithCountDto;
import com.cheko.app.dto.CategoryWithItemListDto;
import com.cheko.app.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryWithCountDto>> getAllCategoriesWithCount() {
        List<CategoryWithCountDto> categoriesWithCount = categoryService.getAllCategoriesWithCount();
        return ResponseEntity.ok(categoriesWithCount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryWithItemListDto> getCategoryById(@PathVariable Long id) {
        CategoryWithItemListDto categoryWithItems = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryWithItems);
    }
}