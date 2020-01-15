package com.michon.fruitshopapi.services;

import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.repositories.CategoryRepository;
import com.michon.fruitshopapi.repositories.CustomerRepository;
import com.michon.fruitshopapi.runner.BootRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception {

        BootRunner bootRunner = new BootRunner(categoryRepository, customerRepository);
        bootRunner.run();

        customerService = new CustomerServiceImpl(customerRepository);
    }

    private Long getCustomerIdValue(){

       return customerRepository.findAll()
               .stream().findFirst()
               .map(x->x.getId())
               .get();
    }

    @Test
    public void patchCustomerUpdateFirstNAmeTest(){
        String updatedName = "NewName";
        long id = getCustomerIdValue();

        Customer previousCustomer = customerRepository.getOne(id);
        assertNotNull(previousCustomer);

        String previousLastName = previousCustomer.getLastName();

        Customer customer = new Customer();
        customer.setFirstName(updatedName);

        customerService.patchCustomer(id, customer);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertEquals(previousLastName, updatedCustomer.getLastName());
    }
}
