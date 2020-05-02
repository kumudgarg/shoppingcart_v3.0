package com.thoughtworks.shoppingcart;
import lombok.Data;

@Data
public class Product {
    private String name;

    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
    }
}
