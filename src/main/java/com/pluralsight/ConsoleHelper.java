package com.pluralsight;

import java.util.Scanner;

public class ConsoleHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static char promptForChar(String message) {
        System.out.print(message + " ");
        String input = scanner.nextLine();
        return input.isEmpty() ? ' ' : input.charAt(0);
    }

    public static int promptForInt(String message) {
        System.out.print(message + " ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

}
