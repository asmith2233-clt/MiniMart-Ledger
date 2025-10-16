package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

// This class represents a single transaction record
public class Transactions {

    // --- Instance variables (properties of a transaction) ---
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // --- Getters: allow access to private variables ---
    public String getVendor() {
        return vendor;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    // --- Constructor: used to create a new Transactions object ---
    public Transactions(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // --- toString method: controls how the transaction prints when displayed ---
    @Override
    public String toString() {
        // Formats the data neatly into columns
        return String.format("%-30s | %-30s | %-30s | %-30s | %-50.2f",
                date, time, description, vendor, amount);
    }
}

