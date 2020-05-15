package com.thoughtworks.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.thoughtworks.shoppingcart.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor

public class CartItem {



    @JsonView
    private Product product;

    @JsonView
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @JsonIgnore
    public String getName() {
        return product.getName();
    }

    @JsonIgnore
    public void increaseQuantity(int extraQuantity) {
        this.quantity += extraQuantity;
    }

    @JsonIgnore
    public double getPrice() {
        return product.getPrice() * quantity;
    }

    @JsonIgnore
    public double getDiscount() {
        double discount = 0.0;
        for (BuyXGetYOffer buyXGetYOffer: product.getOffer()) {
            if (buyXGetYOffer != null) {
                discount = buyXGetYOffer.getDiscount(product, quantity);
                return discount;
            }
        }
        return discount;

    }

}
