package com.interview.ExpenseManagerAPI.Repository;

import com.interview.ExpenseManagerAPI.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends JpaRepository<Expense , Long> {


    @Query("SELECT SUM(ex.amount) FROM Expense  ex where ex.month_expense = :month and ex.year_expense = :year")
    Double  getTotalAmountFormonthAndYear(@Param("month") int month, @Param("year")int year);

}
