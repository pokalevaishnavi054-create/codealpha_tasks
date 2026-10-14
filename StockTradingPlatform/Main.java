import java.util.*;

        public class Main {

            static Scanner sc = new Scanner(System.in);

            static HashMap<String, Double> stocks = new HashMap<>();
            static HashMap<String, Integer> portfolio = new HashMap<>();
            static ArrayList<String> transactions = new ArrayList<>();

            static double balance = 100000;

            public static void main(String[] args) {

                stocks.put("TCS", 3500.0);
                stocks.put("INFY", 1800.0);
                stocks.put("RELIANCE", 2500.0);
                stocks.put("WIPRO", 500.0);

                while (true) {
                    System.out.println("\n===== STOCK TRADING PLATFORM =====");
                    System.out.println("1. View Stocks");
                    System.out.println("2. Buy Stocks");
                    System.out.println("3. Sell Stocks");
                    System.out.println("4. View Portfolio");
                    System.out.println("5. Transaction History");
                    System.out.println("6. Check Balance");
                    System.out.println("7. Exit");

                    System.out.print("Enter your choice: ");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            viewStocks();
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
                            System.out.println("Available Balance: Rs. " + balance);
                            break;
                        case 7:
                            System.out.println("Thank you for using the platform!");
                            return;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            }

            static void viewStocks() {
                System.out.println("\n--- Available Stocks ---");
                for (String stock : stocks.keySet()) {
                    System.out.println(stock + " : Rs. " + stocks.get(stock));
                }
            }

            static void buyStock() {
                viewStocks();

                System.out.print("Enter stock name: ");
                String stock = sc.next().toUpperCase();

                if (!stocks.containsKey(stock)) {
                    System.out.println("Stock not found!");
                    return;
                }

                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();

                if (quantity <= 0) {
                    System.out.println("Quantity must be positive!");
                    return;
                }

                double cost = stocks.get(stock) * quantity;

                if (cost > balance) {
                    System.out.println("Insufficient balance!");
                    return;
                }

                balance -= cost;
                portfolio.put(stock,
                        portfolio.getOrDefault(stock, 0) + quantity);

                transactions.add("Bought " + quantity + " shares of " + stock
                        + " for Rs. " + cost);

                System.out.println("Stock purchased successfully!");
            }

            static void sellStock() {
                viewPortfolio();

                System.out.print("Enter stock name to sell: ");
                String stock = sc.next().toUpperCase();

                if (!portfolio.containsKey(stock)) {
                    System.out.println("You do not own this stock!");
                    return;
                }

                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();

                if (quantity <= 0 || quantity > portfolio.get(stock)) {
                    System.out.println("Invalid quantity!");
                    return;
                }

                double amount = stocks.get(stock) * quantity;
                balance += amount;

                int remaining = portfolio.get(stock) - quantity;

                if (remaining == 0) {
                    portfolio.remove(stock);
                } else {
                    portfolio.put(stock, remaining);
                }

                transactions.add("Sold " + quantity + " shares of " + stock
                        + " for Rs. " + amount);

                System.out.println("Stock sold successfully!");
            }

            static void viewPortfolio() {
                System.out.println("\n--- Your Portfolio ---");

                if (portfolio.isEmpty()) {
                    System.out.println("No stocks owned yet.");
                    return;
                }

                double totalValue = 0;

                for (String stock : portfolio.keySet()) {
                    int quantity = portfolio.get(stock);
                    double value = stocks.get(stock) * quantity;

                    System.out.println(stock + " | Quantity: " + quantity
                            + " | Value: Rs. " + value);

                    totalValue += value;
                }

                System.out.println("Total Portfolio Value: Rs. " + totalValue);
            }

            static void viewTransactions() {
                System.out.println("\n--- Transaction History ---");

                if (transactions.isEmpty()) {
                    System.out.println("No transactions yet.");
                    return;
                }

                for (String transaction : transactions) {
                    System.out.println(transaction);
                }
            }
        }


