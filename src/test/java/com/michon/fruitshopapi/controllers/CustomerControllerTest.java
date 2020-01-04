package com.michon.fruitshopapi.controllers;

import com.michon.fruitshopapi.controllers.CustomerController;
import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.services.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String BASE_URL = "/customers";

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        reset(customerService);
    }

    @Test
    public void testGetAllCustomers() throws Exception {

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("James");
        customer2.setLastName("Brown");
        List<Customer> customers = Arrays.asList(customer1, customer2);
        given(customerService.getAllCustomers()).willReturn(customers);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(customers.size())));
    }

    @Test
    public void testGetCustomerById() throws Exception{

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        given(customerService.getCustomerById(anyLong())).willReturn(customer);

        mockMvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())));
    }
}