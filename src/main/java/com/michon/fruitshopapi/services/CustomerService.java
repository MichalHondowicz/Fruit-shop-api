package com.michon.fruitshopapi.services;

import com.michon.fruitshopapi.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer createNewCustomer(Customer customer);

    Customer updateCustomer(Long id, Customer customer);

    Customer patchCustomer(Long id, Customer customer);

    void deleteCustomerById(Long id);
}
