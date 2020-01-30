package pl.oratynski.newOrder;

import pl.oratynski.model.Order;

import java.util.Scanner;

public class UserOrder {

    Order order = new Order();
    Scanner sc = new Scanner(System.in);

    public Order createOrder() {

        System.out.print("Select order product: ");
        order.setProducts_id(sc.nextInt());
        System.out.print("Enter order customer: ");
        order.setCustomers_id(sc.nextInt());
        System.out.print("Enter quantity: ");
        order.setQty(sc.nextInt());

        return order;
    }

}
