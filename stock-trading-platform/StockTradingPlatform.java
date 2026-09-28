import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Stock> stocks = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();

    static User user = new User("User", 100000);

    public static void main(String[] args) {

        
        stocks.add(new Stock("TCS", "Tata Consultancy Services", 3500));
        stocks.add(new Stock("INFY", "Infosys", 1800));
        stocks.add(new Stock("RELIANCE", "Reliance Industries", 2900));
        stocks.add(new Stock("HDFC", "HDFC Bank", 1700));

        int choice;

        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    viewMarketData();
                    break;

                case 2:
                    buyStock();
                    break;

                case 3:
                    sellStock();
                    break;

                case 4:
                    viewPortfolio();
                    break;

                case 5:
                    viewTransactions();
                    break;

                case 6:
                    System.out.println("Thank you for using Stock Trading Platform!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        scanner.close();
    }

    
    static void viewMarketData() {

        System.out.println("\n===== MARKET DATA =====");

        for (Stock stock : stocks) {
            stock.displayStock();
        }
    }

    
    static void buyStock() {

        viewMarketData();

        System.out.print("\nEnter stock symbol to buy: ");
        String symbol = scanner.next();

        Stock selectedStock = findStock(symbol);

        if (selectedStock == null) {
            System.out.println("Stock not found!");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        double totalCost = selectedStock.price * quantity;

        if (totalCost > user.balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        user.balance -= totalCost;

        Stock boughtStock = new Stock(
        selectedStock.symbol,
        selectedStock.name,
        selectedStock.price
);

boughtStock.quantity = quantity;

user.portfolio.add(boughtStock);

        transactions.add(
                new Transaction("BUY",
                        selectedStock.symbol,
                        quantity,
                        selectedStock.price)
        );

        System.out.println("Stock purchased successfully!");
        System.out.println("Total cost: Rs" + totalCost);
    }


    static void sellStock() {

        System.out.print("\nEnter stock symbol to sell: ");
        String symbol = scanner.next();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        int count = 0;

for (Stock stock : user.portfolio) {
    if (stock.symbol.equalsIgnoreCase(symbol)) {
        count += stock.quantity;
    }
}

        if (count < quantity) {
            System.out.println("You don't have enough shares!");
            return;
        }

        Stock selectedStock = findStock(symbol);

        double totalValue = selectedStock.price * quantity;
for (int i = user.portfolio.size() - 1; i >= 0; i--) {

    Stock stock = user.portfolio.get(i);

    if (stock.symbol.equalsIgnoreCase(symbol)) {

        stock.quantity -= quantity;

        if (stock.quantity == 0) {
            user.portfolio.remove(i);
        }

        break;
    }
}
        user.balance += totalValue;

        transactions.add(
                new Transaction("SELL",
                        selectedStock.symbol,
                        quantity,
                        selectedStock.price)
        );

        System.out.println("Stock sold successfully!");
        System.out.println("Amount received: Rs" + totalValue);
    }

    
    static void viewPortfolio() {

        System.out.println("\n===== MY PORTFOLIO =====");

        if (user.portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {

            for (Stock stock : user.portfolio) {
                System.out.println(
                        stock.symbol + " - " +
                        stock.name + " - ₹" +
                        stock.price
                );
            }
        }

        System.out.println("Available Balance: ₹" + user.balance);
    }

    
    static void viewTransactions() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {

            for (Transaction transaction : transactions) {
                transaction.displayTransaction();
            }
        }
    }

    
    static Stock findStock(String symbol) {

        for (Stock stock : stocks) {

            if (stock.symbol.equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }
}
