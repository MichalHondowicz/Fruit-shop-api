package com.michon.fruitshopapi.service;

import com.michon.fruitshopapi.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

   Category getCategoryByName(String name);

    List<Category> getAllCategories();
}
