package com.michon.fruitshopapi.controllers;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.domain.CategoryList;
import com.michon.fruitshopapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/categories";

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryList> getAllCategories() {
        return new ResponseEntity<>(
                new CategoryList(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String categoryName) {
        return new ResponseEntity<>(categoryService.getCategoryByName(categoryName), HttpStatus.OK);
    }
}
