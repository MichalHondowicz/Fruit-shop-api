package com.michon.fruitshopapi.controllers;

import com.michon.fruitshopapi.controllers.CustomerController;
import com.michon.fruitshopapi.services.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.reset;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        reset(customerService);
    }

}