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
                .orElseThrow(ResourceNotFoundException::new);
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

    @Override
    public Customer patchCustomer(Long id, Customer customerPatch) {
        return customerRepository.findById(id).map(customer -> {
            if (customerPatch.getFirstName() != null) {
                customer.setFirstName(customerPatch.getFirstName());
            }
            if (customerPatch.getLastName() != null) {
                customer.setLastName(customerPatch.getLastName());
            }
            return customerRepository.save(customer);
        }).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public void deleteCustomerById(Long id) {

        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}
