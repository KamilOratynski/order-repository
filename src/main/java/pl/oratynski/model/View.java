package pl.oratynski.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class View {

    private int id;
    private LocalDateTime date;
    private int qty;
    private String nameCustomer;
    private String nameProduct;

}
