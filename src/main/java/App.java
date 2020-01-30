import pl.oratynski.model.Order;
import pl.oratynski.model.View;
import pl.oratynski.newOrder.UserOrder;
import pl.oratynski.repository.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class App {

    private static Repository repository = new Repository();
    private static UserOrder userOrder = new UserOrder();

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=shop_schema?useUnicode=yes&characterEncoding=UTF-8";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, props);
            repository.setConnection(connection);

            run();

            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void run() throws SQLException {
        /**insert order to database*/
        /*Order order = userOrder.createOrder();
        repository.insertOrders(order);*/

        /**three last order*/
        /*System.out.println("List three last orders:");
        List<Order> lastOrders = repository.threeLastOrders(3);
        lastOrders.forEach(System.out::println);
        System.out.println();*/

        /**last 24h orders*/
        /*System.out.println("List 24h last orders:");
        List<Order> lastOrders24h = repository.lastOrders24h();
        lastOrders24h.forEach(System.out::println);
        System.out.println();*/

        /**last 24h orders view*/
        System.out.println("List 24h last orders in view:");
        List<View> lastOrders24HView = repository.lastOrders24HView();
        lastOrders24HView.forEach(System.out::println);
        System.out.println();
    }

}
