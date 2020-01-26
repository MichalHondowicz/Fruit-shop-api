package com.michon.fruitshopapi.services;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.exceptions.category.CategoryNotFoundException;
import com.michon.fruitshopapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository
                .findByName(name)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
