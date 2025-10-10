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
                 5- Quit the application
                """;
            System.out.println(homeMenu);

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