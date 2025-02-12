import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;
public class DataBase {
    public Connection connect_to_db(String db_name, String db_user, String db_pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + db_name, db_user, db_pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
