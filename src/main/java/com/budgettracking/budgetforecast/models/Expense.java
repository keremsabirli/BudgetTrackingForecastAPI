package com.budgettracking.budgetforecast.models;

import java.util.Date;

public class Expense {
    private String name;
    private double amount;
    private String description;
    private String city;
    private Date date;

    public Expense(String name, double amount, String description, String city, Date date) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.city = city;
        this.date = date;
    }
    
    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}