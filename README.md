# Mini Mart Ledger App

## Overview
The **Mini Mart Ledger App** is a simple console-based application to manage and track financial transactions for a mini mart. It allows the user to add deposits, make payments, and view the ledger, including detailed reports filtered by date or vendor. All transactions are stored in a CSV file for persistent record-keeping.

---

## Features

### Home Screen
When the app starts, the user is presented with the following options:

- **D) Add Deposit** – Prompts the user for deposit details (date, time, description, vendor, and amount) and saves it to `transactions.csv`.
- **P) Make Payment (Debit)** – Prompts the user for payment details and saves it as a negative amount in the CSV file.
- **L) Ledger** – Opens the ledger menu to view all transactions and reports.
- **X) Exit** – Exits the application.

> The app continues running until the user chooses to exit.

---

### Ledger Menu
The ledger menu displays transactions in **newest-first order**. Options include:

- **A) All Entries** – Displays all transactions.
- **D) Deposits** – Displays only positive entries (deposits).
- **P) Payments** – Displays only negative entries (payments).
- **R) Reports** – Opens a sub-menu for generating reports:
  - **1) Month To Date** – Shows transactions from the start of the current month.
  - **2) Previous Month** – Shows transactions from the previous month.
  - **3) Year To Date** – Shows transactions from the start of the current year.
  - **4) Previous Year** – Shows transactions from the previous year.
  - **5) Search by Vendor** – Prompts the user for a vendor name and displays matching transactions.
  - **0) Back** – Returns to the Ledger Menu.
- **H) Home** – Returns to the Home Screen.

---

## How to Run

1. Ensure you have **Java** installed on your machine.
2. Compile all `.java` files:
   ```bash
   javac com/pluralsight/*.java
