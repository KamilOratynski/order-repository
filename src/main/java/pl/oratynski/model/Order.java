package pl.oratynski.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {

    private int products_id;
    private int customers_id;
    private int employers_id;
    private int qty;
    private LocalDateTime date;

}
