import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class App {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=shop_schema?useUnicode=yes&characterEncoding=UTF-8";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, props);


            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
