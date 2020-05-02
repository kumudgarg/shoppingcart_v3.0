package com.thoughtworks.shoppingcart;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();

    private double totalPrice;


    private CartItem findCartItem(String name) {
        return cartItems.stream().filter(cartItem -> cartItem.getName() == name).findFirst().orElse(null);
    }

    private double updateTotalPrice() {
        totalPrice = cartItems.stream().mapToDouble(cartItem -> cartItem.getPrice()).sum();
        return totalPrice;
    }

    public void addToCart(Product product, int quantity) {
        CartItem existingCartItem = findCartItem(product.getName());
        if (existingCartItem != null)
            existingCartItem.increaseQuantity(quantity);
        else {
            CartItem newCartItem = new CartItem(product, quantity);
            cartItems.add(newCartItem);
        }

        updateTotalPrice();
    }

    public double getTotal() {
        return totalPrice;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(cartItems);
    }


    public double getSalesTax() {
        return  (totalPrice * 2) / 100;
    }

    public double getTotalWithSalesTax(){
        return totalPrice += getSalesTax();
    }
}
