import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static List<Product> cart = new ArrayList<>();
    private static OrderService orderService = new OrderService();
    private static ProductService productService = new ProductService();

    public static void main(String[] args) {
        DataBase db = new DataBase();
        Connection conn = db.connect_to_db("Endterm", "postgres", "A67Hayl11");
        UserService userService = new UserService();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Welcome to the Shoe E-Shop =====");
            System.out.println("1. Create an account");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createAccount(userService, conn, input);
                    break;
                case 2:
                    logIn(userService, conn, input);
                    break;
                case 3:
                    System.out.println("Exiting. Goodbye!");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void createAccount(UserService userService, Connection conn, Scanner input) {
        System.out.println("\n=== Create Account ===");
        System.out.print("Enter your account name: ");
        String name = input.nextLine();

        System.out.print("Enter your email: ");
        String email = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        System.out.print("Re-enter your password: ");
        String passwordAgain = input.nextLine();

        if (!password.equals(passwordAgain)) {
            System.out.println("Passwords do not match. Account not created.");
            return;
        }

        if (userService.userExists(conn, name, email, password)) {
            System.out.println("Account already exists. Please choose a different name.");
        } else {
            userService.addUserToTable(conn, name, email, password);
            System.out.println("Account created successfully!");
        }
    }

    private static void logIn(UserService userService, Connection conn, Scanner input) {
        System.out.println("\n=== Log In ===");

        System.out.print("Enter your account name: ");
        String name = input.nextLine();

        System.out.print("Enter your email: ");
        String email = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        if (userService.userExists(conn, name, email, password)) {
            System.out.println("Login successful! Welcome, " + name + "!");
            userMenu(user, conn, input);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void userMenu(User user, Connection conn, Scanner input) {
        while (true) {
            System.out.println("\n===== User Dashboard =====");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    productService.getUserTable(conn);
                    break;
                case 2:
                    System.out.print("Enter Product ID to add to cart: ");
                    int productId = input.nextInt();
                    input.nextLine();
                    Product product = productService.getProductById(conn, productId);
                    if (product != null) {
                        cart.add(product);
                        System.out.println(product.getProductName() + " added to cart.");
                    } else {
                        System.out.println("Invalid Product ID.");
                    }
                    break;
                case 3:
                    System.out.println("\n===== Cart Contents =====");
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        for (Product p : cart) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty. Add products first.");
                    } else {
                        orderService.placeOrder(conn, user.getName(), cart);
                        cart.clear();
                        System.out.println("Order placed successfully!");
                    }
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
