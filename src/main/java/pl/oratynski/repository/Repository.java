package pl.oratynski.repository;

import pl.oratynski.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static final String INSERT_INTO_ORDERS_PRODUCTS_ID_CUSTOMERS_ID_QTY = "INSERT INTO shop_schema.orders (products_id,customers_id,qty) VALUES (?,?,?);";
    private static final String SELECT_FROM_ORDERS_THREE_LAST_ORDERS = "SELECT * FROM shop_schema.orders ORDER BY id DESC LIMIT ?;";
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

    private Order getParameters(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setProducts_id(resultSet.getInt("products_id"));
        order.setCustomers_id(resultSet.getInt("customers_id"));
        order.setEmployers_id(resultSet.getInt("employers_id"));
        order.setQty(resultSet.getInt("qty"));
        order.setDate(resultSet.getTimestamp("date").toLocalDateTime());
        return order;
    }
}
