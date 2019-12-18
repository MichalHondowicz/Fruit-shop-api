package com.michon.fruitshopapi.service;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.exceptions.CategoryNameException;
import com.michon.fruitshopapi.exceptions.CategoryNotFoundException;
import com.michon.fruitshopapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(name));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        try {
            category.setName(category.getName().toUpperCase());
            return categoryRepository.save(category);
        } catch (Exception ex) {
            throw new CategoryNameException(category.getName().toUpperCase());
        }
    }

    @Override
    public void deleteCategoryByName(String namne) {

    }
}
