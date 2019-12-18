package com.michon.fruitshopapi.service;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.repository.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Test";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void getCategoryByName() {

        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> categoriesTest = categoryService.getAllCategories();

        assertEquals(3, categoriesTest.size());
    }

    @Test
    void getAllCategories() {

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));

        Category testCategory = categoryService.getCategoryByName(NAME);

        assertEquals(ID, testCategory.getId());
        assertEquals(NAME, testCategory.getName());
    }
}