package com.budgettracking.budgetforecast.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.budgettracking.budgetforecast.models.Expense;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mlforecast.ForecastManager;
import mlforecast.WeekToWeek;

@RestController
public class ExpenseController {

    @GetMapping("/api/Expense")
    public Expense greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Expense("Kerem", 20, "BLABLA", "Istanbul", new Date());
    }
    @PostMapping("/api/Expense/Forecast/WeekToWeek")
    @ResponseBody
    public Double forecast(@RequestBody ArrayList<Double> expenses) {
        ForecastManager forecastManager = new ForecastManager(new WeekToWeek(expenses));
        List<Double> result = forecastManager.DoForecast();
        return result.get(0);
    }
}