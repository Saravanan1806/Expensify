package com.example.Expense_Tracker.Expensify.Service;

import com.example.Expense_Tracker.Expensify.Model.Expensify;
import com.example.Expense_Tracker.Expensify.Repository.ExpensifyRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensifyService {

    private final ExpensifyRepo expensifyRepo;

    public ExpensifyService(ExpensifyRepo expensifyRepo){
        this.expensifyRepo=expensifyRepo;
    }

    public List<Expensify> getAllExpenses(){
        return expensifyRepo.findAll();
    }

    public Expensify getExpenseById(long id){
        return expensifyRepo.findById(id).orElse(null);
    }

    public void saveExpense(Expensify expensify){
        expensifyRepo.save(expensify);
    }

    public void delExpense(long id){
        expensifyRepo.deleteById(id);
    }
}
