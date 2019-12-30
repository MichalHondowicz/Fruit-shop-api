package com.michon.fruitshopapi.services;

import com.michon.fruitshopapi.domain.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryByName(String name);

    List<Category> getAllCategories();
}
