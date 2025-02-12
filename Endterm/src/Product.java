public class Product {
    private static int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String productCategory;

    public Product(int productId,String productName, double productPrice, int productQuantity, String productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
    }

    public int getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    public String getProductCategory() {
        return productCategory;
    }
}
