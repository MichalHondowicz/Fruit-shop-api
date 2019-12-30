package com.michon.fruitshopapi.runner;

import com.michon.fruitshopapi.domain.Category;
import com.michon.fruitshopapi.domain.Customer;
import com.michon.fruitshopapi.repository.CategoryRepository;
import com.michon.fruitshopapi.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootRunner implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public BootRunner(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        setUpCategories();
        setUpCustomers();
    }

    private void setUpCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");
        Category dried = new Category();
        dried.setName("Dried");
        Category fresh = new Category();
        fresh.setName("Fresh");
        Category exotic = new Category();
        exotic.setName("Exotic");
        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
    }

    private void setUpCustomers() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customerRepository.save(customer);

        Customer customer1 = new Customer();
        customer.setId(2L);
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customerRepository.save(customer1);
    }
}
