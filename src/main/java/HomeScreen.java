import java.util.Scanner;

public class HomeScreen {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
    public static void homeMenu(){
        String homeMenu = """
                \n======== Ledger App =====
                Welcome to the Mini Mart!
                \n======= HomeMenu ======
               
                 D) Add Deposit
                 P) Make Payment (Debit)
                 L) Ledger
                 X) Exit
                """;
            System.out.println(homeMenu);
        while (true) {
            System.out.print(homeMenu);

            char command;
            command= InputCollector.promptForChar("Enter your command");

            switch (command) {
                case 'D':
                    display deposits();
                    break;
                case 'P':
                    Make a payment();
                case 'L':
                    showLedger();
                    break;
                case 'X':
                    goToExit();
                    break;

                default:
                    System.out.println("INVALID COMMAND!! Please select a valid option.");
                    break;


    }
    public static void ledgerMenu(){
        String ledgerMenu = """
                \n======== Ledger Menu =======
                A) All Entries
                D) Deposits
                P) Payments
                R) Reports
                H) Home
                """;
        System.out.println(ledgerMenu);

        while (true) {
            System.out.print(ledgerMenu);

            char command;
            command= InputCollector.promptForChar("Enter your command");

            switch (command) {
                case 'A':
                    displayAllEntries();
                    break;
                case 'D':
                    displayDeposits();
                case 'P':
                    makePayments();
                    break;
                case 'R':
                    boolean goToMain;
                    break;
                case 'H':
                    return;
                default:
                    System.out.println("INVALID COMMAND!! Please select a valid option.");
                    break;
            }

    }
    public static void reportMenu(){
        String reportMenu = """
                /n===== Report Menu =====
                1) Month to Date
                2) Previous Month
                3) Year to Date
                4) Previous Year
                5) Search by User
                0) Back
                """;
    }

}