package com.cheko.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheko.app.dto.CategoryWithCountDto;
import com.cheko.app.dto.CategoryWithItemListDto;
import com.cheko.app.dto.MenuEntityDto;
import com.cheko.app.entities.CategoryEntity;
import com.cheko.app.entities.MenuEntity;
import com.cheko.app.repositories.CategoryRepository;
import com.cheko.app.repositories.MenuRepository;

@Service
public class CategoryService {

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private MenuRepository menuRepository;

        public List<CategoryWithCountDto> getAllCategoriesWithCount() {
                List<CategoryEntity> categories = categoryRepository.findAll();

                return categories.stream()
                                .map(category -> {
                                        Long menuCount = menuRepository.countByCategory(category);
                                        return new CategoryWithCountDto(
                                                        category.getId(),
                                                        category.getName(),
                                                        category.getImageUrl(),
                                                        menuCount);
                                })
                                .collect(Collectors.toList());
        }

        public CategoryWithItemListDto getCategoryById(Long id) {
                CategoryEntity category = categoryRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

                List<MenuEntity> menuItems = menuRepository.findByCategory(category);

                List<MenuEntityDto> menuItemDtos = menuItems.stream()
                                .map(MenuEntityDto::new)
                                .collect(Collectors.toList());

                return new CategoryWithItemListDto(
                                category.getId(),
                                category.getName(),
                                menuItemDtos);
        }

}