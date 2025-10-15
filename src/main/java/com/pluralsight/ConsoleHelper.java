package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleHelper {

    private static final Scanner scanner = new Scanner(System.in);

    // Safely prompt for integer input
    public static int promptForInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    // Safely prompt for double input
    public static double promptForDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine();
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    // Prompt for a text input
    public static String promptForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    // Prompt for a valid date (YYYY-MM-DD)
    public static LocalDate promptForDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String dateAsString = scanner.nextLine().trim();
                return LocalDate.parse(dateAsString);
            } catch (Exception ex) {
                System.out.println("Invalid entry! Please enter a valid date (YYYY-MM-DD).");
            }
        }
    }

    // Prompt for a valid time (HH:MM:SS)
    public static LocalTime promptForTime(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String timeAsString = scanner.nextLine().trim();
                return LocalTime.parse(timeAsString);
            } catch (Exception ex) {
                System.out.println("Invalid entry! Please enter a valid time (HH:MM:SS).");
            }
        }
    }
}

