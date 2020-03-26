package com.example.expenseproject.repository;

import com.example.expenseproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// connect category object to the database, ID type is a Long
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // findBy + FieldName
    Category findByName(String name); // return a category name
}
