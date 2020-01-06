package com.michon.fruitshopapi.services;

import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.exceptions.customer.CustomerNotFoundException;
import com.michon.fruitshopapi.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

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

    @Override
    public Customer createNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerToBeSaved = customer;
        customerToBeSaved.setId(id);
        return customerRepository.save(customerToBeSaved);
    }
}
