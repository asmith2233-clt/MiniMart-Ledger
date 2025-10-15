package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transactions {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;


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

    public Transactions(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;


    }

        @Override
        public String toString() {
            return String.format("%-30s | %-30s | %-30s | %-30s | %-50.2f",
                    date, time, description, vendor, amount);
        }

    }


