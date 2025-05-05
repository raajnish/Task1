import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance = 10000; // Initial balance

    void buyStock(String stockName, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Insufficient funds!");
            return;
        }
        balance -= cost;
        holdings.put(stockName, holdings.getOrDefault(stockName, 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + stockName);
    }

    void sellStock(String stockName, int quantity, double price) {
        if (!holdings.containsKey(stockName) || holdings.get(stockName) < quantity) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        balance += quantity * price;
        holdings.put(stockName, holdings.get(stockName) - quantity);
        System.out.println("Sold " + quantity + " shares of " + stockName);
    }

    void viewPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio();
        List<Stock> market = Arrays.asList(
            new Stock("Apple", 150.0),
            new Stock("Tesla", 200.0),
            new Stock("Amazon", 100.0)
        );

        while (true) {
            System.out.println("\n1. View Market Data\n2. Buy Stock\n3. Sell Stock\n4. View Portfolio\n5. Exit");
            int choice = scanner.nextInt();
            if (choice == 5) break;

            switch (choice) {
                case 1:
                    System.out.println("Market Data:");
                    for (Stock stock : market) {
                        System.out.println(stock.name + " - $" + stock.price);
                    }
                    break;
                case 2:
                    System.out.println("Enter stock name and quantity to buy:");
                    String buyStock = scanner.next();
                    int buyQty = scanner.nextInt();
                    for (Stock stock : market) {
                        if (stock.name.equalsIgnoreCase(buyStock)) {
                            portfolio.buyStock(stock.name, buyQty, stock.price);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter stock name and quantity to sell:");
                    String sellStock = scanner.next();
                    int sellQty = scanner.nextInt();
                    for (Stock stock : market) {
                        if (stock.name.equalsIgnoreCase(sellStock)) {
                            portfolio.sellStock(stock.name, sellQty, stock.price);
                        }
                    }
                    break;
                case 4:
                    portfolio.viewPortfolio();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }
}