public class Stock {
    String symbol;
    String name;
    double price;
    int quantity;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    public void displayStock() {
        System.out.println(symbol + " - " + name + " - Rs." + price);
    }
}
