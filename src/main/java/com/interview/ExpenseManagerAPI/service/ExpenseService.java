package com.interview.ExpenseManagerAPI.service;


import com.interview.ExpenseManagerAPI.Repository.ExpenseRepository;
import com.interview.ExpenseManagerAPI.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    public ExpenseRepository expenseRepository;

    public Expense addExpense(Expense e) {
        LocalDate now = LocalDate.now();
        e.setDate(now);
        int monthValue = now.getMonthValue();
        e.setMonth_expense(monthValue);
        int year = now.getYear();
        e.setYear_expense(year);
        expenseRepository.save(e);
        return e;
    }

    public List<Expense> getAllExpense() {
        List<Expense> all = expenseRepository.findAll();
        return all;
    }

    public Expense getExpenseById(long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.orElse(null);
    }

    public Double  getExpenseMonthWithYear(int month, int year) {
       Double amount = expenseRepository.getTotalAmountFormonthAndYear(month,year);

       return amount != null ? amount : 0.0;
    }


}
