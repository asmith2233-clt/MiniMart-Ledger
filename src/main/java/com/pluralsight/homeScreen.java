package com.pluralsight;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class homeScreen {


    public static ArrayList<Transactions> transactions = getTransactionsFromFile();

    public static void main(String[] args) {
        homeMenu();
    }


    public static void homeMenu() {
        String homeMenu = """
                \n======== Ledger App =====
                Welcome to the Mini Mart!
                \n======= Home Menu ======
                 D) Add Deposit
                 P) Make Payment (Debit)
                 L) Ledger
                 X) Exit
                """;

        while (true) {
            System.out.print(homeMenu);
            String command = ConsoleHelper.promptForString("Enter Command (D, P, L, X)").toUpperCase();

            switch (command) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> ledgerMenu();
                case "X" -> {
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("INVALID COMMAND! Please select a valid option.");
            }
        }
    }

   public static void ledgerMenu() {
        String ledgerMenu = """
                \n======== Ledger Menu =======
                A) All Entries
                D) Deposits
                P) Payments
                R) Reports
                H) Home
                """;

        while (true) {
            System.out.print(ledgerMenu);
            String command = ConsoleHelper.promptForString("Enter your command (A, D, P, R, H)").toUpperCase();

            switch (command) {
                case "A" -> displayAllEntries();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> reportMenu();
                case "H" -> { return; }
                default -> System.out.println("INVALID COMMAND! Please select a valid option.");
            }
        }
    }


    public static void reportMenu() {
        String reportMenu = """
                \n===== Report Menu =====
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                0) Back
                """;

        while (true) {
            System.out.print(reportMenu);
            int command = ConsoleHelper.promptForInt("Enter your command");

            switch (command) {
                case 1 -> reportMonthToDate();
                case 2 -> reportPreviousMonth();
                case 3 -> reportYearToDate();
                case 4 -> reportPreviousYear();
                case 5 -> searchByVendor();
                case 0 -> { return; }
                default -> System.out.println("INVALID COMMAND! Please select a valid option.");
            }
        }
    }


    private static void addDeposit() {
        System.out.println("Add Deposit");
        LocalDate date = ConsoleHelper.promptForDate("Enter Date (yyyy-mm-dd)");
        LocalTime time = ConsoleHelper.promptForTime("Enter Time (HH:mm:ss)");
        String description = ConsoleHelper.promptForString("Enter Description");
        String vendor = ConsoleHelper.promptForString("Enter Vendor");
        double amount = Math.abs(ConsoleHelper.promptForDouble("Enter Amount"));

        Transactions t = new Transactions(date, time, description, vendor, amount);
        transactions.add(t);
        writeTransactionToFile(t);

        System.out.println("Deposit added successfully.");
    }

    private static void makePayment() {
        System.out.println("Make Payment (Debit)");
        LocalDate date = ConsoleHelper.promptForDate("Enter Date (yyyy-mm-dd)");
        LocalTime time = ConsoleHelper.promptForTime("Enter Time (HH:mm:ss)");
        String description = ConsoleHelper.promptForString("Enter Description");
        String vendor = ConsoleHelper.promptForString("Enter Vendor");
        double amount = -Math.abs(ConsoleHelper.promptForDouble("Enter Amount"));

        Transactions t = new Transactions(date, time, description, vendor, amount);
        transactions.add(t);
        writeTransactionToFile(t);

        System.out.println("Payment added successfully.");
    }


    private static void writeTransactionToFile(Transactions t) {

        try (FileWriter writer = new FileWriter("transactions.csv" ,true);
            // Write header if file doesn’t exist yet

             PrintWriter pw = new PrintWriter(writer)) {
            pw.printf("%s|%s|%s|%s|%.2f%n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());

        } catch (IOException e) {
            System.out.println("Error writing transaction: " + e.getMessage());
        }
    }

    public static ArrayList<Transactions> getTransactionsFromFile() {
        ArrayList<Transactions> transactions = new ArrayList<>();



        try (FileReader fileReader = new FileReader("transactions.csv");
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Skip header or unrelated text lines
                if (isFirstLine && line.toLowerCase().contains("date")) {
                    isFirstLine = false;
                    continue;
                }
                if (!line.contains("|")) continue;

                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                try {
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    LocalTime time = LocalTime.parse(parts[1].trim());
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());

                    Transactions t = new Transactions(date, time, description, vendor, amount);
                    transactions.add(t);
                } catch (DateTimeParseException | NumberFormatException ignored) {
                    // Skip invalid lines quietly
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        // Sort newest first
        transactions.sort(Comparator.comparing(Transactions::getDate)
                .thenComparing(Transactions::getTime)
                .reversed());

        return transactions;
    }


    private static void displayTransactions(ArrayList<Transactions> list) {
        if (list.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-12s %-10s %-20s %-15s %10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-------------------------------------------------------------");

        for (Transactions t : list) {
            System.out.printf("%-12s %-10s %-20s %-15s %10.2f%n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }

        System.out.println("-------------------------------------------------------------");
    }

    private static void displayAllEntries() {
        System.out.println("Displaying All Entries (Newest First)...");
        displayTransactions(transactions);
    }

    private static void displayDeposits() {
        ArrayList<Transactions> deposits = new ArrayList<>();
        for (Transactions t : transactions) {
            if (t.getAmount() > 0) deposits.add(t);
        }
        displayTransactions(deposits);
    }

    private static void displayPayments() {
        ArrayList<Transactions> payments = new ArrayList<>();
        for (Transactions t : transactions) {
            if (t.getAmount() < 0) payments.add(t);
        }
        displayTransactions(payments);
    }


    private static void reportMonthToDate() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);
        ArrayList<Transactions> results = new ArrayList<>();

        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(start)) {
                results.add(t);
            }
        }
        displayTransactions(results);
    }

    private static void reportPreviousMonth() {
        YearMonth current = YearMonth.now();
        YearMonth previous = current.minusMonths(1);
        LocalDate start = previous.atDay(1);
        LocalDate end = previous.atEndOfMonth();
        ArrayList<Transactions> results = new ArrayList<>();

        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                results.add(t);
            }
        }
        displayTransactions(results);
    }

    private static void reportYearToDate() {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
        ArrayList<Transactions> results = new ArrayList<>();

        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(startOfYear)) {
                results.add(t);
            }
        }
        displayTransactions(results);
    }

    private static void reportPreviousYear() {
        int year = LocalDate.now().getYear() - 1;
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        ArrayList<Transactions> results = new ArrayList<>();

        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                results.add(t);
            }
        }
        displayTransactions(results);
    }

    private static void searchByVendor() {
        String vendorSearch = ConsoleHelper.promptForString("Enter vendor name to search");
        ArrayList<Transactions> results = new ArrayList<>();

        for (Transactions t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendorSearch.trim())) {
                results.add(t);
            }
        }
        displayTransactions(results);
    }
}
