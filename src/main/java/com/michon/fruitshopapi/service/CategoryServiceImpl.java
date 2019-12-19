package com.michon.fruitshopapi.service;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.exception.CategoryNameException;
import com.michon.fruitshopapi.exception.CategoryNotFoundException;
import com.michon.fruitshopapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository
                .findByName(name).orElseThrow(() -> new CategoryNotFoundException(name));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
