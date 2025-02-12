import java.util.Scanner;
import java.sql.Connection;
public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        db.connect_to_db("Endterm", "postgres", "A67Hayl11");
    }
}