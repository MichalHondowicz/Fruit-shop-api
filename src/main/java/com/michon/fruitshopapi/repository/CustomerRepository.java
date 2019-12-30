package com.michon.fruitshopapi.repository;

import com.michon.fruitshopapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}


