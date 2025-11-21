package com.example.Expense_Tracker.Expensify.Repository;

import com.example.Expense_Tracker.Expensify.Model.Expensify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensifyRepo extends JpaRepository<Expensify, Long> {
    @Query(value = "SELECT COALESCE(SUM(amount), 0) FROM Expenses", nativeQuery = true)
    Long totalTest();
}
