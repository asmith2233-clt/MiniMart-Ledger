Mini Mart Ledger Application
# Project Description

The Mini Mart Ledger App is a console-based Java program that helps users track financial transactions for a small business such as a gas station or convenience store.

It allows users to:

Add deposits (money received)

Record payments (money spent)

View all transactions in a clear table format

Generate reports by month, year, or vendor

Automatically save and load data from a .csv file

This project simulates how a real business ledger operates, giving store owners an easy way to manage finances directly from the terminal.

# Application Screens
## Home Screen

This is the main menu that appears when the application starts.
It allows the user to add deposits, make payments, view the ledger, or exit the app.

Screenshot:
<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/39b24028-fe1c-44ab-9587-23432e76f851" />



Example Output:

======== Ledger App =====
 Welcome to the Mini Mart!
======= Home Menu ======
 D) Add Deposit
 P) Make Payment (Debit)
 L) Ledger
 X) Exit
Enter Command (D, P, L, X):

## Transactions.csv File

This file stores all recorded transactions.
Each line represents a single entry with the following format:

date|time|description|vendor|amount


Screenshot:
<img width="648" height="351" alt="image" src="https://github.com/user-attachments/assets/d3c91336-9af7-49dc-903d-931c59adae05" />



# Interesting Piece of Code
## transactions.csv + Home Menu (Saving and Loading Data)

One of the most interesting parts of this project is how the app saves and retrieves data using the transactions.csv file.
This turns the program into a persistent ledger, meaning your data isn’t lost when the program closes.

// Save transaction to CSV file
private static void writeTransactionToFile(Transactions t) {
    try (FileWriter writer = new FileWriter("transactions.csv", true);
         PrintWriter pw = new PrintWriter(writer)) {
        pw.printf("%s|%s|%s|%s|%.2f%n",
                t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
    } catch (IOException e) {
        System.out.println("Error writing transaction: " + e.getMessage());
    }
}


This code automatically appends each new transaction to the CSV file when you add a deposit or payment.
When the app restarts, it reads that file and loads all past entries, keeping your ledger up to date.

# Technologies Used

Language: Java

Packages Used: java.io, java.time, java.util

File Type: .csv for storing transactions

Tools: IntelliJ IDEA / VS Code

# Future Improvements

If I could add one new feature, it would be an automatic balance calculator that shows the store’s total current balance after each transaction.
This would make it even more practical for managing Mini Mart finances in real time.

# Author

Ayanna Smith
Student Project — Mini Mart Ledger

“I created this ledger app as a step toward managing the kind of Mini Mart I hope to run one day.”
