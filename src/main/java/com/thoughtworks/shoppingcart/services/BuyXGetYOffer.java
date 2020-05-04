package com.thoughtworks.shoppingcart.services;

import com.fasterxml.jackson.annotation.JsonView;
import com.thoughtworks.shoppingcart.model.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class BuyXGetYOffer {

    @JsonView
    @NotNull @NotBlank
    private int buyQuantity;

    @JsonView
    @NotNull @NotBlank
    private int freeQuantity;

    public BuyXGetYOffer(int buyQuantity, int freeQuantity) {
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
    }

    public double getDiscount(Product product, int quantity) {
        if(freeQuantity != 0 && buyQuantity != 0) {
            int freeItems = (quantity / (freeQuantity + buyQuantity)) * freeQuantity;
            return freeItems * product.getPrice();
        }
        return 0.0;
    }
}
