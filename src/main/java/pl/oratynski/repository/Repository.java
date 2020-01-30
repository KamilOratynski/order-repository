package pl.oratynski.repository;

import pl.oratynski.model.Order;
import pl.oratynski.model.View;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static final String INSERT_INTO_ORDERS_PRODUCTS_ID_CUSTOMERS_ID_QTY = "INSERT INTO shop_schema.orders (products_id,customers_id,qty) VALUES (?,?,?);";
    private static final String SELECT_FROM_ORDERS_THREE_LAST_ORDERS = "SELECT * FROM shop_schema.orders ORDER BY id DESC LIMIT ?;";
    private static final String SELECT_FROM_ORDERS_LAST_24H_ORDERS = "SELECT * FROM shop_schema.orders WHERE \"date\" > ? ORDER BY \"date\" DESC;";
    private static final String SELECT_FROM_ORDERS_LAST_24H_VIEW = "SELECT * FROM shop_schema.view WHERE \"date\" > ? ORDER BY \"date\" DESC;";
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int insertOrders(Order order) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_INTO_ORDERS_PRODUCTS_ID_CUSTOMERS_ID_QTY);

        statement.setInt(1, order.getProducts_id());
        statement.setInt(2, order.getCustomers_id());
        statement.setInt(3, order.getQty());

        return statement.executeUpdate();
    }

    public List<Order> threeLastOrders(int limit) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_ORDERS_THREE_LAST_ORDERS);
        statement.setInt(1, limit);
        ResultSet resultSet = statement.executeQuery();

        List<Order> listThreeLastOrders = new ArrayList<>();
        while (resultSet.next()) {
            listThreeLastOrders.add((getParameters(resultSet)));
        }

        return listThreeLastOrders;
    }

    public List<Order> lastOrders24h() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_ORDERS_LAST_24H_ORDERS);
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        statement.setTimestamp(1, Timestamp.from(localDateTime.toInstant(ZoneOffset.UTC)));
        ResultSet resultSet = statement.executeQuery();

        List<Order> listLast24Orders = new ArrayList<>();
        while (resultSet.next()) {
            listLast24Orders.add((getParameters(resultSet)));
        }

        return listLast24Orders;
    }

    public List<View> lastOrders24HView() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_ORDERS_LAST_24H_VIEW);
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        statement.setTimestamp(1, Timestamp.from(localDateTime.toInstant(ZoneOffset.UTC)));
        ResultSet resultSet = statement.executeQuery();

        List<View> listLast24Orders = new ArrayList<>();
        while (resultSet.next()) {
            listLast24Orders.add((getParametersView(resultSet)));
        }

        return listLast24Orders;
    }

    private Order getParameters(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setProducts_id(resultSet.getInt("products_id"));
        order.setCustomers_id(resultSet.getInt("customers_id"));
        order.setEmployers_id(resultSet.getInt("employers_id"));
        order.setQty(resultSet.getInt("qty"));
        order.setDate(resultSet.getTimestamp("date").toLocalDateTime());
        return order;
    }

    private View getParametersView(ResultSet resultSet) throws SQLException {
        View view = new View();
        view.setId(resultSet.getInt("id"));
        view.setDate(resultSet.getTimestamp("date").toLocalDateTime());
        view.setQty(resultSet.getInt("qty"));
        view.setNameCustomer(resultSet.getString("name_customer"));
        view.setNameProduct(resultSet.getString("name_product"));
        return view;
    }

}
