package com.thoughtworks.shoppingcart.model;
import com.fasterxml.jackson.annotation.JsonView;
import com.thoughtworks.shoppingcart.services.BuyXGetYOffer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Product {

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
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

    public BuyXGetYOffer getOffer() {
        return offer;
    }
}
