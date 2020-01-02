package com.michon.fruitshopapi.services;

import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    public static final Long ID = 1L;
    public static String FIRST_NAME = "Adam";
    public static final String LAST_NAME = "Jones";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void GetAllCustomers(){
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        given(customerRepository.findAll()).willReturn(customers);

        List<Customer> customers1 = customerService.getAllCustomers();

        assertEquals(3, customers1.size());
    }

    @Test
    public void GetCustomerById() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));

        Customer customer1 = customerService.getCustomerById(ID);

        assertEquals(ID, customer1.getId());
        assertEquals(FIRST_NAME, customer1.getFirstName());
        assertEquals(LAST_NAME, customer1.getLastName());
    }

    @Test
    public void createNewCustomer() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Bob");
        customer.setLastName("James");
        given(customerRepository.save(any(Customer.class))).willReturn(customer);

        Customer customerToSave = customerService.createNewCustomer(customer);

        assertEquals(customerToSave.getId(), customer.getId());
        assertEquals(customerToSave.getFirstName(), customer.getFirstName());
        assertEquals(customerToSave.getLastName(), customer.getLastName());
    }
}