package com.example.expenseproject.controller;

import com.example.expenseproject.model.Expense;
import com.example.expenseproject.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired // tell spring to get a copy of that guy and set it up - injects expenserepository into ExpenseController
    private ExpenseRepository expenseRepository;

    // Get and return a list of expenses
    @GetMapping("/expenses")
    List<Expense> getExpenses() {
        return expenseRepository.findAll(); // SELECT * FROM expenses;
    }

    // delete a specific expense
    @DeleteMapping("/expenses/{id}") // <?> = generic model
    ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id); // delete by Id
        return ResponseEntity.ok().build(); // just remove it without returning anything
    }

    // Create a new expense
    @PostMapping("/expenses")
    ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) throws URISyntaxException {
        Expense result = expenseRepository.save(expense);
        return ResponseEntity.created(new URI("/api/expenses" + result.getId())).body(result);
    }

    // Update an Expense
    @PutMapping("/expenses/{id}")
    ResponseEntity<Expense> updateExpense(@Valid @RequestBody Expense expense){ // return response entity of expense
        Expense result= expenseRepository.save(expense);
        return ResponseEntity.ok().body(result);
    }

}
