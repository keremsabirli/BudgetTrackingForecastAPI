package com.budgettracking.budgetforecast.controllers;

import java.util.ArrayList;
import java.util.Date;

import com.budgettracking.budgetforecast.models.Expense;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
    @GetMapping("/api/Expense")
    public Expense greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Expense("Kerem", 20, "BLABLA", "Istanbul", new Date());
    }

    @PostMapping("/api/Expense")
    @ResponseBody
    public ArrayList<Expense> forecast(@RequestBody ArrayList<Expense> expense) {
        return expense;
    }
}