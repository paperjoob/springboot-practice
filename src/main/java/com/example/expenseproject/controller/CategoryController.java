package com.example.expenseproject.controller;

import com.example.expenseproject.model.Category;
import com.example.expenseproject.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController // converts response to JSON/XML automatically
@RequestMapping("/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    // constructor - pass in the category and reassign it
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // GET ALL CATEGORIES and return a collection of categories
    @GetMapping("/categories")
    Collection<Category> categories() {
        return categoryRepository.findAll(); // SELECT * FROM CATEGORY;
    }

    // Grab a category by its ID
    @GetMapping("/category/{id}") // path variable
    ResponseEntity<?> getCategory(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id); // optional because the ID might not be valid
        return category.map(response -> ResponseEntity.ok().body(response)). // the return is mapped to the response
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // otherwise, send the status not found to the browser
    }

    // Post a new category
    @PostMapping("/category")
    ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException { // expecting a valid request body of category
        // save the category
        Category result= categoryRepository.save(category);
        return ResponseEntity.created(new URI("/api/category" + result.getId())).body(result);
    }

    // Modify a category
    @PutMapping("/category/{id}")
    ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category){ // return response entity of category
        Category result= categoryRepository.save(category);
        return ResponseEntity.ok().body(result);
    }

    // Delete a category
    @DeleteMapping("/category/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build(); // build a response body of OK with no body
    }
}
