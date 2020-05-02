package com.thoughtworks.shoppingcart;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();

    private double totalPrice;

    private double salesTax;

    private double discount;


    private CartItem findCartItem(String name) {
        return cartItems.stream().filter(cartItem -> cartItem.getName() == name).findFirst().orElse(null);
    }

    private double updateActualTotalPrice() {
        totalPrice = cartItems.stream().mapToDouble(cartItem -> cartItem.getPrice()).sum();
        return totalPrice;
    }

    private double updateSalesTax() {
        salesTax = MoneyUtility.getSalesTax(totalPrice - discount);
        return MoneyUtility.format(salesTax);
    }

    private double updateTotalPrice() {
        return totalPrice += salesTax - discount;
    }



    public void addToCart(Product product, int quantity) {
        CartItem existingCartItem = findCartItem(product.getName());
        if (existingCartItem != null)
            existingCartItem.increaseQuantity(quantity);
        else {
            CartItem newCartItem = new CartItem(product, quantity);
            cartItems.add(newCartItem);
        }
        updateActualTotalPrice();
        updateSalesTax();
        updateTotalPrice();
    }

    public double getTotal() {
        return totalPrice;
    }

    public double getSalesTax() {
        return salesTax;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(cartItems);
    }


    public double getTotalDiscount() {
      return 0.0;
    }
}
