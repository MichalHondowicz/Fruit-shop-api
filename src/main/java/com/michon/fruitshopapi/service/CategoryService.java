package com.michon.fruitshopapi.service;

import com.michon.fruitshopapi.domain.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category saveCategory(Category category);

    void deleteCategoryByName(String namne);
}
