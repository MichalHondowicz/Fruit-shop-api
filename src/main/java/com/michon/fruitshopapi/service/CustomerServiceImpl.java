package com.michon.fruitshopapi.service;

import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.exception.customer.CustomerNotFoundException;
import com.michon.fruitshopapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
