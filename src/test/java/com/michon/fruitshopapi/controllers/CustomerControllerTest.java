package com.michon.fruitshopapi.controllers;

import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.services.CustomerService;
import com.michon.fruitshopapi.services.ResourceNotFoundException;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest extends TestRestControllerExtensionMethods {

    private static final String BASE_URL = CustomerController.BASE_URL;
    private static final long ID = 1L;
    private int id = (int) ID;
    private String customerId = String.format("/%d", id);
    private static final String NOT_FOUND_ID = "/1000";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private Customer customer;

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(ID);
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
    public void testGetCustomerByIdNotFound() throws Exception {

        given(customerService.getCustomerById(anyLong())).willThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(BASE_URL + NOT_FOUND_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        given(customerService.createNewCustomer(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void testUpdateCustomer() throws Exception {

        given(customerService.updateCustomer(anyLong(), any(Customer.class))).willReturn(customer);

        mockMvc.perform(put(BASE_URL + customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())));
    }

    @Test
    public void testPatchCustomer() throws Exception {

        given(customerService.patchCustomer(anyLong(), any(Customer.class))).willReturn(customer);

        mockMvc.perform(patch(BASE_URL + customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(id)))
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(BASE_URL + customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }
}