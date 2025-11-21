package com.example.Expense_Tracker.Expensify.Controller;

import com.example.Expense_Tracker.Expensify.Model.Expensify;
import com.example.Expense_Tracker.Expensify.Repository.ExpensifyRepo;
import com.example.Expense_Tracker.Expensify.Service.ExpensifyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExpensifyController {

    private final ExpensifyRepo expensifyRepo;
    private final ExpensifyService expensifyService;

    public ExpensifyController(ExpensifyRepo expensifyRepo, ExpensifyService expensifyService) {
        this.expensifyRepo = expensifyRepo;
        this.expensifyService = expensifyService;
    }

    @GetMapping("/home")
    public String getAllExpenses(Model model) {
        List<Expensify> expensifyList = expensifyService.getAllExpenses();
        model.addAttribute("totalAmount", expensifyRepo.totalTest());
        model.addAttribute("expenses", expensifyList);
        return "index";
    }

    @GetMapping("/addExpense")
    public String addExpense(Model model) {
        Expensify expensify = new Expensify();
        model.addAttribute("expense", expensify);
        return "add-expense";
    }

    @GetMapping("/editExpense/{id}")
    public String editExpense(@PathVariable("id") long id, Model model) {
        Expensify expensify = expensifyService.getExpenseById(id);
        model.addAttribute("expense", expensify);
        return "update-expense";
    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute("expense") Expensify expensify, Model model) {
        expensifyService.saveExpense(expensify);
        return "redirect:/";
    }

    @PostMapping("/updateExpense/{id}")
    public String updateExpense(@PathVariable("id") long id, @ModelAttribute("expense") Expensify expensify,
            Model model) {
        Expensify expensify1 = expensifyService.getExpenseById(id);
        expensify1.setDescription(expensify.getDescription());
        expensify1.setAmount(expensify.getAmount());
        expensifyService.saveExpense(expensify1);
        return "redirect:/";
    }

    @GetMapping("/deleteExpense/{id}")
    public String deleteExpense(@PathVariable("id") long id, Model model) {
        expensifyService.delExpense(id);
        return "redirect:/";
    }

}
