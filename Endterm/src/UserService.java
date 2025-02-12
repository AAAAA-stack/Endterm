import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class UserService {
    public void createUserTable (Connection conn) {
        Statement statement;
        try {
            String query = "CREATE TABLE users(userId Serial PRIMARY KEY , userName varchar(200), userEmail varchar(200), userPassword varchar(200), userPhone varchar(200), userAddress varchar(200));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void addUserToTable (Connection conn, String userName, String userEmail, String userPassword, String userPhone, String userAddress) {
        Statement statement;
        try {
            String query = String.format("INSERT INTO users(userName, userEmail, userPassword, userPhone, userAddress) VALUES ('%s', '%s', '%s', '%s', '%s');)", userName, userEmail, userPassword, userPhone, userAddress);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void addUserToTable (Connection conn, String userName, String userEmail, String userPassword) {
        Statement statement;
        try {
            String query = String.format("INSERT INTO users(userName, userEmail, userPassword, userPhone, userAddress) VALUES ('%s', '%s', '%s');)", userName, userEmail, userPassword);
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
            String query = "SELECT * FROM users;";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            while (rs.next()) {
                System.out.print(rs.getString("userId") + " ");
                System.out.print(rs.getString("userName")+ " ");
                System.out.print(rs.getString("userEmail")+ " ");
                System.out.print(rs.getString("userPassword")+ " ");
                System.out.print(rs.getString("userPhone")+ " ");
                System.out.println(rs.getString("userAddress"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean userExists(Connection conn, String userName, String userEmail, String userPassword) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM users WHERE userName = '%s' AND userEmail = '%s' AND userPassword = '%s';", userName, userEmail, userPassword);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            return rs.next(); // If rs has a result, user exists
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public User userTableInfoToObject(Connection conn, String userName, String userEmail, String userPassword) {
        String query = "SELECT * FROM users WHERE userName = ? AND userEmail = ? AND userPassword = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, userEmail);
            pstmt.setString(3, userPassword);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("userId"), rs.getString("userName"), rs.getString("userEmail"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if no user is found
    }

}
