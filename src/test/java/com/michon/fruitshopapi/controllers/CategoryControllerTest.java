package com.michon.fruitshopapi.controllers;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.services.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    public static final String NAME = "Test";
    public static final String BASE_URL = "/categories";

    @MockBean
    CategoryService categoryService;

    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        reset(categoryService);
    }

    @Test
    public void testGetAllCategories() throws Exception {

        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("TestCategory1");
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName(NAME);
        List<Category> categories = Arrays.asList(category1, category2);
        given(categoryService.getAllCategories()).willReturn(categories);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(categories.size())));
    }

    @Test
    public void testGetCategoryByName() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName(NAME);
        given(categoryService.getCategoryByName(anyString())).willReturn(category);

        mockMvc.perform(get(BASE_URL + "/Test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(category.getName())));
    }
}