package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class homeScreen {

    public static ArrayList<Transactions>  transactions = getTransactionsFromFile();

    public static void main(String[] args) {

        // Start the app by showing the home menu
        homeMenu();
    }

    public static void homeMenu() {
        String homeMenu = """
                \n======== Ledger App =====
                Welcome to the Mini Mart!
                \n======= HomeMenu ======
                 D) Add Deposit
                 P) Make Payment (Debit)
                 L) Ledger
                 X) Exit
                """;

        while (true) {
            System.out.print(homeMenu);

            String command = ConsoleHelper.promptForString("Enter Command: 'D', 'p', 'L', 'X' ");

            switch (command) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerMenu();
                    break;
                case "X":
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("INVALID COMMAND!! Please select a valid option.");
                    break;
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

            String command = ConsoleHelper.promptForString("Enter your command: ");

            switch (command) {
                case "A":
                    displayAllEntries();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportMenu();
                    break;
                case "H":
                    return; // back to home menu
                default:
                    System.out.println("INVALID COMMAND!! Please select a valid option.");
                    break;
            }
        }
    }

    public static void reportMenu() {
        String reportMenu = """
                \n===== Report Menu =====
                1) Month to Date
                2) Previous Month
                3) Year to Date
                4) Previous Year
                5) Search by Vendor
                0) Back
                """;

        while (true) {
            System.out.print(reportMenu);

            int command = ConsoleHelper.promptForInt("Enter your command: ");

            switch (command) {
                case 1:
                    reportMonthToDate();
                    break;
                case 2:
                    reportPreviousMonth();
                    break;
                case 3:
                    reportYearToDate();
                    break;
                case 4:
                    reportPreviousYear();
                    break;
                case 5:
                    searchByVendor();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("INVALID COMMAND!! Please select a valid option.");
                    break;
            }
        }
    }

    public static void addDeposit() {
        System.out.println("Add Deposit ");
        LocalDate date = ConsoleHelper.promptForDate("Enter Date yyyy-mm-dd");
        LocalTime time = ConsoleHelper.promptForTime("Enter time HH:mm:ss");
        String description = ConsoleHelper.promptForString("Enter description ");
        String vendor = ConsoleHelper.promptForString("Enter vendor ");
        double amount = ConsoleHelper.promptForDouble("Enter Amount ");


        Transactions transaction = promptForTransaction(amount);
        Transactions t = new Transactions(date, time, description, vendor, amount);

        System.out.println("Deposit added.");

        // write the info that we got to the csv file

    }

    public static void makePayment() {
        LocalDate date = ConsoleHelper.promptForDate("Enter Date yyyy-mm-dd");
        LocalTime time = ConsoleHelper.promptForTime("Enter time HH:mm:ss");
        String description = ConsoleHelper.promptForString("Enter description ");
        String vendor = ConsoleHelper.promptForString("Enter vendor ");
        double amount = ConsoleHelper.promptForDouble("Enter Amount ");

        amount = -Math.abs(amount); // ensure negative for payment

        Transactions t = new Transactions(date, time, description, vendor, amount);
        transactions.add(t);

        try {
            writeTransactionToFile(t);
        } catch (IOException e) {
            System.out.println("Error saving payment: " + e.getMessage());
        }
        System.out.println("Payment added.");

    }

    private static void writeTransactionToFile(Transactions t) throws IOException {
        try (FileWriter writer = new FileWriter("Transactions.csv", true)) {
            writer.write(String.format("%s|%s|%s|%s|%.2f%n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<Transactions> getTransactionsFromFile() {
        ArrayList<Transactions> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Transactions.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 5) continue;

                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                transactions.add(new Transactions(date, time, description, vendor, amount));
            }
        } catch (IOException | DateTimeParseException | NumberFormatException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
        return transactions;


    }

    private static Transactions promptForTransaction(double amount) {
        return null;

    }

    public static void displayAllEntries() {
        System.out.println("Displaying all entries...");
        for (Transactions t : transactions) {
            System.out.printf("%s | %s | %s | %s | %.2f%n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }

    }

    public static void displayDeposits() {
        System.out.println("Displaying deposits...");
        for (Transactions t : transactions) {
            if (t.getAmount() > 0) {
                System.out.printf("%s | %s | %s | %s | %.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void displayPayments() {
        System.out.println("Displaying payments...");
        for (Transactions t : transactions) {
            if (t.getAmount() < 0) {
                System.out.printf("%s | %s | %s | %s | %.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void reportMonthToDate() {
        System.out.println("Report: Month to Date...");
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);

        System.out.println("==== Month to Date Transactions ====");
        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(startOfMonth) && !t.getDate().isAfter(today)) {
                System.out.println(t);
            }
        }
    }

    public static void reportPreviousMonth() {
        System.out.println("Report: Previous Month...");
        YearMonth current = YearMonth.now();
        YearMonth previous = current.minusMonths(1);

        LocalDate start = previous.atDay(1);
        LocalDate end = previous.atEndOfMonth();

        System.out.println("==== Previous Month Transactions ====");
        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                System.out.println(t);
            }
        }
    }

    public static void reportYearToDate() {
        System.out.println("Report: Year to Date...");
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);

        System.out.println("==== Year to Date Transactions ====");
        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(startOfYear) && !t.getDate().isAfter(today)) {
                System.out.println(t);
            }
        }
    }

    public static void reportPreviousYear() {
        System.out.println("Report: Previous Year...");
        int previousYear = LocalDate.now().getYear() - 1;

        LocalDate start = LocalDate.of(previousYear, 1, 1);
        LocalDate end = LocalDate.of(previousYear, 12, 31);

        System.out.println("==== Previous Year Transactions ====");
        for (Transactions t : transactions) {
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                System.out.println(t);
            }
        }
    }

    public static void searchByVendor() {
        System.out.println("Searching by vendor...");
        String vendorSearch = ConsoleHelper.promptForString("Enter vendor name to search");

        System.out.println("==== Transactions for Vendor: " + vendorSearch + " ====");
        for (Transactions t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendorSearch)) {
                System.out.println(t);
            }
        }
    }


}



