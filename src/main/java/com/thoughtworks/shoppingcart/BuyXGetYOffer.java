package com.thoughtworks.shoppingcart;

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
