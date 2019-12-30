package com.michon.fruitshopapi.repositories;

import com.michon.fruitshopapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}


