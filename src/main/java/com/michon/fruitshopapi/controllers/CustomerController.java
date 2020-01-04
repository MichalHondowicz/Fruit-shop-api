package com.michon.fruitshopapi.controllers;


import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.domain.CustomerList;
import com.michon.fruitshopapi.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerList> getAllCustomers() {
        return new ResponseEntity<>(new CustomerList(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createNewCustomer(customer), HttpStatus.CREATED);
    }
}
