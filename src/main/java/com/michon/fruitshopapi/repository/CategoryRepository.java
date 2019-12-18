package com.michon.fruitshopapi.repository;

import com.michon.fruitshopapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

    
}
