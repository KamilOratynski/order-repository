package pl.oratynski.repository;

import pl.oratynski.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Repository {

    private static final String INSERT_INTO_ORDERS_PRODUCTS_ID_CUSTOMERS_ID_QTY = "INSERT INTO shop_schema.orders (products_id,customers_id,qty) VALUES (?,?,?);";
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

}
