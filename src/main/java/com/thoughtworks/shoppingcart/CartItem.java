package com.thoughtworks.shoppingcart;

public class CartItem {

    private Product product;

    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getName() {
        return product.getName();
    }

    public void increaseQuantity(int extraQuantity) {
        this.quantity += extraQuantity;
    }

    public double getPrice() {
        return product.getPrice() * quantity;
    }

    public double getDiscount() {
        double discount = 0.0;
        if(product.getOffer() != null){
            discount = product.getOffer().getDiscount(product, quantity);

        }
        return discount;
    }
}
