import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ProductService {
        public void createUserTable (Connection conn) {
            Statement statement;
            try {
                String query = "CREATE TABLE products(producId Serial Primary Key, productName varchar(200), productPrice double, productQuantity int, productCategory varchar(200));";
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("Table created");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        public void addProductToTable (Connection conn, String productName, Double productPrice, int productQuantity, String productCategory) {
            Statement statement;
            try {
                String query = String.format("INSERT INTO products(productName, productPrice, productQuantity, productCategory) VALUES ('%s', '%s', '%s', '%s');)", productName, productPrice, productQuantity, productCategory);
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("Table created");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        public void getUserTable (Connection conn) {
            Statement statement;
            ResultSet rs = null;
            try{
                String query = "SELECT * FROM products;";
                statement = conn.createStatement();
                statement.executeUpdate(query);
                while (rs.next()) {
                    System.out.print(rs.getString("productId") + " ");
                    System.out.print(rs.getString("productName")+ " ");
                    System.out.print(rs.getDouble("productPrice")+ " ");
                    System.out.print(rs.getInt("productQuantity")+ " ");
                    System.out.println(rs.getString("productCategory"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }


        public Product getProductById(Connection conn, int productId) {
            String query = "SELECT * FROM products WHERE productId = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, productId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return new Product(
                                rs.getInt("productId"),
                                rs.getString("productName"),
                                rs.getDouble("productPrice"),
                                rs.getInt("productQuantity"),
                                rs.getString("productCategory")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if product not found
    }

}
