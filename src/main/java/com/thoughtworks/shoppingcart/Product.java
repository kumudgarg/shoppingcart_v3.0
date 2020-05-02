package com.thoughtworks.shoppingcart;
import lombok.Data;

@Data
public class Product {
    private String name;

    private double price;

    private BuyXGetYOffer offer;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.offer = null;
    }

    public Product(String name, double price, BuyXGetYOffer offer) {
        this.name = name;
        this.price = price;
        this.offer = offer;
    }


    public Product() {
    }


    public BuyXGetYOffer getOffer() {
        return offer;
    }
}
