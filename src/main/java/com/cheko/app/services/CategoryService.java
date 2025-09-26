package com.cheko.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.cheko.app.dto.CategoryEntityDto;
import com.cheko.app.repositories.CategoryRepository;

public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<CategoryEntityDto> retrieveAllCategories() {
        return repository.findAll().stream().map(x -> new CategoryEntityDto(x)).collect(Collectors.toList());
    }

}
