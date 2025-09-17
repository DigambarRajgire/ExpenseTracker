package com.interview.ExpenseManagerAPI.controller;


import com.interview.ExpenseManagerAPI.entity.Expense;
import com.interview.ExpenseManagerAPI.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Expencse")
@RequiredArgsConstructor
public class ExpenseController {


//    @Autowired
    public final ExpenseService expenseService;


    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense e){
        expenseService.addExpense(e);
        return ResponseEntity.ok(e);

    }

    @GetMapping("/getAllExpense")
    public List<Expense> fetAllExpense(){
        List<Expense> allExpense = expenseService.getAllExpense();
        return ResponseEntity.ok(allExpense).getBody();
    }

    @GetMapping("/getExpenseById/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable long id){
        Expense expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/getExpenseByMonthWithYear")
    public ResponseEntity<?> getExpenseByMonthWithYear(@RequestParam int month , @RequestParam int year){
        double expenseMonthWithYear = expenseService.getExpenseMonthWithYear(month, year);
        return ResponseEntity.ok(expenseMonthWithYear);
    }
}
