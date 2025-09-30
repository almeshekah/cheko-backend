package com.cheko.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheko.app.dto.CategoryWithMenuItemsSearchDto;
import com.cheko.app.dto.MenuEntityDto;
import com.cheko.app.services.MenuService;

@RestController
@RequestMapping("/menuItem")
public class MenuController {
    @Autowired
    private MenuService service;

    @GetMapping
    public List<MenuEntityDto> list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category) {
        return service.search(q, category);
    }

    @GetMapping("/{id}")
    public MenuEntityDto retrieveOne(@PathVariable Long id) {
        return service.retrieveOneItem(id);
    }

    @GetMapping("/list-by-category")
    public List<CategoryWithMenuItemsSearchDto> searchGroupedByCategory(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category) {
        return service.searchGroupedByCategory(q, category);
    }

    @GetMapping("/second-highest-calorie-per-category")
    public List<CategoryWithMenuItemsSearchDto> getSecondHighestCaloriePerCategory() {
        return service.getSecondHighestCaloriePerCategory();
    }
}
