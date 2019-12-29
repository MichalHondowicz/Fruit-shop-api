package com.michon.fruitshopapi.controller.v1;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.domain.CategoryList;
import com.michon.fruitshopapi.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryList> getAllCategories() {
        return new ResponseEntity<>(
                new CategoryList(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{categoryName}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String categoryName) {
        return new ResponseEntity<>(categoryService.getCategoryByName(categoryName), HttpStatus.OK);
    }
}
