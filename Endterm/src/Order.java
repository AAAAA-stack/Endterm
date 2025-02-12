import java.util.ArrayList;

public class Order {
    private static int orderId;
    private int customerId;
    private ArrayList<Product> products;
    private ArrayList<Integer> productIdList;
    private ArrayList<Integer> quantity;
    private double orderTotalPrice;

    Order(int orderId, int customerId, ArrayList<Product> products, int quantity, double orderTotalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.products = products;
        this.productIdList = new ArrayList<>();
        this.quantity = new ArrayList<>();
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getOrderId() {
        return orderId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public ArrayList<Integer> getProductIdList() {
        return productIdList;
    }
    public ArrayList<Integer> getQuantity() {
        return quantity;
    }
    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }
}
