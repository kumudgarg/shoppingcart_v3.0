package com.thoughtworks.shoppingcart.services;

import com.thoughtworks.shoppingcart.model.Product;
import org.springframework.stereotype.Service;

@Service
public class BuyXGetYOffer {

    private int buyQuantity;
    private int freeQuantity;

    public BuyXGetYOffer(int buyQuantity, int freeQuantity) {
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
    }

    public double getDiscount(Product product, int quantity) {
        int freeItems = (quantity / (freeQuantity + buyQuantity)) * freeQuantity;
        return freeItems * product.getPrice();
    }
}
