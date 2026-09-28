import java.util.ArrayList;

public class User {
    String name;
    double balance;
    ArrayList<Stock> portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        portfolio = new ArrayList<>();
    }
}
