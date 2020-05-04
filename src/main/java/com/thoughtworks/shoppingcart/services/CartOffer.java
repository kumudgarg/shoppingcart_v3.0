package com.thoughtworks.shoppingcart.services;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CartOffer {

    @JsonView
    @NotNull @NotBlank
    private double discountRate;

    @JsonView
    @NotNull @NotBlank
    private int leastBuyPrice;

    private double cartDiscount = 0.0;

    public CartOffer(double discountRate, int leastBuyPrice) {
        this.discountRate = discountRate;
        this.leastBuyPrice = leastBuyPrice;
    }

    public double getDiscountByCartOffer(double totalPrice, double discount) {
        if (totalPrice > leastBuyPrice) {
            cartDiscount = ((totalPrice - discount) * discountRate) / 100;
        }
        return cartDiscount;
    }
}
