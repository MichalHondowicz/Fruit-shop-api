package com.michon.fruitshopapi.controllers;

import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.services.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest extends TestRestControllerExtensionMethods{

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String BASE_URL = "/customers";
    Customer customer;

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
    }

    @AfterEach
    void tearDown() {
        reset(customerService);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("James");
        customer2.setLastName("Brown");
        List<Customer> customers = Arrays.asList(customer, customer2);
        given(customerService.getAllCustomers()).willReturn(customers);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(customers.size())));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        given(customerService.getCustomerById(anyLong())).willReturn(customer);

        mockMvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())));
    }

    @Test
    public void testCreateNewCustomer() throws Exception{
        given(customerService.createNewCustomer(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void testSaveCustomer() throws Exception{
        given(customerService.saveCustomer(anyLong(), any(Customer.class))).willReturn(customer);

        mockMvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())));
    }
}