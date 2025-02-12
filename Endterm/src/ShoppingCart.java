import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    // Constructor
    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    // Add a product to the cart
    public void addProduct(Product product) {
        this.products.add(product);
        System.out.println(product.getProductName() + " added to the cart.");
    }

    // Remove a product from the cart by product ID
    public void removeProduct(int productId) {
        Product toRemove = null;
        for (Product product : products) {
            if (product.getProductId() == productId) {
                toRemove = product;
                break;
            }
        }
        if (toRemove != null) {
            products.remove(toRemove);
            System.out.println(toRemove.getProductName() + " removed from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    // Get total price of all products in the cart
    public double getTotalPrice() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getProductPrice();
        }
        return total;
    }

    // Display cart items
    public void displayCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Product product : products) {
                System.out.println(" - " + product.getProductName() + " ($" + product.getProductPrice() + ")");
            }
            System.out.println("Total Price: $" + getTotalPrice());
        }
    }

    // Get list of products in the cart
    public List<Product> getProducts() {
        return products;
    }
}
