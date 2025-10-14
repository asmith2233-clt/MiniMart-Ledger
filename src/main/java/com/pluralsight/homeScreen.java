package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class homeScreen {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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

            char command = ConsoleHelper.promptForChar("Enter your command: ");

            switch (Character.toUpperCase(command)) {
                case 'D':
                    addDeposit();
                    break;
                case 'P':
                    makePayment();
                    break;
                case 'L':
                    ledgerMenu();
                    break;
                case 'X':
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

            char command = ConsoleHelper.promptForChar("Enter your command: ");

            switch (Character.toUpperCase(command)) {
                case 'A':
                    displayAllEntries();
                    break;
                case 'D':
                    displayDeposits();
                    break;
                case 'P':
                    displayPayments();
                    break;
                case 'R':
                    reportMenu();
                    break;
                case 'H':
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
        System.out.println("How much do you want to deposit? ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("What date will this deposit be added? yyyy-mm-dd ");
        String date = scanner.nextLine();
        System.out.println("What time will this be deposited? HH:mm:ss");
        String time = scanner.nextLine();
        System.out.println("Who is the vendor? ");
        String vendor = scanner.nextLine();
        System.out.println("What is the description? ");
        String description = scanner.nextLine();

        LocalDate convertedDate = LocalDate.parse(date);
        LocalTime convertedTime = LocalTime.parse(time);


        Transactions transaction = new Transactions(convertedDate, convertedTime, description, vendor, amount);

        // write the info that we got to the csv file

    }

    public static void makePayment() {
        System.out.println("How much? ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("What date will you make this payment? yyyy-mm-dd ");
        String date = scanner.nextLine();
        System.out.println("What time will you make this payment? HH:mm:ss");
        String time = scanner.nextLine();
        System.out.println("Who is the vendor? ");
        String vendor = scanner.nextLine();
        System.out.println("What is the description? ");
        String description = scanner.nextLine();

        LocalDate convertedDate = LocalDate.parse(date);
        LocalTime convertedTime = LocalTime.parse(time);

    }

    public static void displayAllEntries() {
        System.out.println("Displaying all entries...");
    }

    public static void displayDeposits() {
        System.out.println("Displaying deposits...");
    }

    public static void displayPayments() {
        System.out.println("Displaying payments...");
    }

    public static void reportMonthToDate() {
        System.out.println("Report: Month to Date...");
    }

    public static void reportPreviousMonth() {
        System.out.println("Report: Previous Month...");
    }

    public static void reportYearToDate() {
        System.out.println("Report: Year to Date...");
    }

    public static void reportPreviousYear() {
        System.out.println("Report: Previous Year...");
    }

    public static void searchByVendor() {
        System.out.println("Searching by vendor...");
    }

    public static ArrayList<Transactions> getTransactionsFromFile() {
        ArrayList<Transactions> inventory = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("Transactions.csv");
            BufferedReader br = new BufferedReader(fileReader);

            String lineFromString;

            while ((lineFromString = br.readLine()) != null) {
                String[] parts = lineFromString.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);

                Transactions t = new Transactions();
                inventory.add(t);
            }

        } catch (Exception e) {
            System.out.println("There was an error reading the file!");
        }

        return inventory;

    }
}



