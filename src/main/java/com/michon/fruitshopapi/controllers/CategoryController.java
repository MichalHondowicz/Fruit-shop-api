package com.michon.fruitshopapi.controllers;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.domain.CategoryList;
import com.michon.fruitshopapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/categories";

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryList getAllCategories() {
        return new CategoryList(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public Category getCategoryByName(@PathVariable String categoryName) {
        return categoryService.getCategoryByName(categoryName);
    }
}
