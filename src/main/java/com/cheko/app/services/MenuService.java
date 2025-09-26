package com.cheko.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cheko.app.dto.MenuEntityDto;
import com.cheko.app.entities.MenuEntity;
import com.cheko.app.repositories.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository repository;

    public MenuEntityDto retrieveOneItem(Long id) {
        MenuEntity item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        return new MenuEntityDto(item);
    }

    public List<MenuEntityDto> search(String q, String category) {
        if (q != null && category == null) {
            return repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q)
                    .stream()
                    .map(MenuEntityDto::new)
                    .collect(Collectors.toList());
        }
        if (category != null && q == null) {
            return repository.findByCategory_NameIgnoreCase(category)
                    .stream()
                    .map(MenuEntityDto::new)
                    .collect(Collectors.toList());
        }
        if (q != null && category != null) {
            Specification<MenuEntity> spec = (root, cq, cb) -> cb.and(
                    cb.or(
                            cb.like(cb.lower(root.get("name")), "%" + q.toLowerCase() + "%"),
                            cb.like(cb.lower(root.get("description")), "%" + q.toLowerCase() + "%")),
                    cb.equal(cb.lower(root.join("category").get("name")), category.toLowerCase()));
            return repository.findAll(spec)
                    .stream()
                    .map(MenuEntityDto::new)
                    .collect(Collectors.toList());
        }
        return repository.findAll()
                .stream()
                .map(MenuEntityDto::new)
                .collect(Collectors.toList());
    }

}
