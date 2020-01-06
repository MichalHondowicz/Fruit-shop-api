package com.michon.fruitshopapi.services;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class CategoryServiceTest {

    private static final Long ID = 2L;
    private static final String NAME = "Test";
    private CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        given(categoryRepository.findAll()).willReturn(categories);

        List<Category> categoriesTest = categoryService.getAllCategories();

        assertEquals(3, categoriesTest.size());
    }

    @Test
    void testGetCategoryByName() {
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);
        given(categoryRepository.findByName(anyString())).willReturn(Optional.of(category));

        Category testCategory = categoryService.getCategoryByName(NAME);

        assertEquals(ID, testCategory.getId());
        assertEquals(NAME, testCategory.getName());
    }
}