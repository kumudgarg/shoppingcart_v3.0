package com.thoughtworks.shoppingcart;

public class CartOffer {
    private double discountRate;

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
