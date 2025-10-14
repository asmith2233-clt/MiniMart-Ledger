package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transactions {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transactions() {
    }

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

    public static Transactions fromCSV(String csvline) {
        String[] parts = csvline.split("\\|");
        LocalDate date = LocalDate.parse(parts[0]);
        LocalTime time = LocalTime.parse(parts[1]);
        String description = parts[2];
        String vendor = parts[3];
        double amount = Double.parseDouble(parts[4]);
        return new Transactions();
    }

    @Override
    public String toString() {
        return date + " | " + time + " | " + description + " | " + vendor + " | " + String.format("%.2f", amount);
    }
}

