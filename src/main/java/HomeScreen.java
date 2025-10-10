import java.util.ArrayList;

public class HomeScreen {
    public static ArrayList<Ledger> inventory = getLedgerFromFile();

    private static ArrayList<Ledger> getLedgerFromFile() {
        return null;
    }

    public static void main(String[] args) {

        String mainMenu = """
                What do you want to do?
                 1- List all products
                 2- Lookup a product by its id
                 3- Find all products within a price range
                 4- Add a new product
                 5- Quit the application
                """;



    }
}