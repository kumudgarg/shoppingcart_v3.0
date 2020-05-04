package com.thoughtworks.shoppingcart.services;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.thoughtworks.shoppingcart.Exception.NullProductTypeException;
import com.thoughtworks.shoppingcart.model.Product;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();

    private double totalPrice;

    private double salesTax;

    private double discount;

    private double grandTotal;

    @JsonView
    private CartOffer cartOffer;

    public CartService(CartOffer cartOffer) {
        this.cartOffer = cartOffer;
    }

    public CartService() {
        this.cartOffer = null;
    }

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
        return grandTotal = totalPrice +  salesTax - discount;
    }


    private double updateDiscountByProductOffer() {
        discount = cartItems.stream().mapToDouble(CartItem::getDiscount).sum();
        return discount;
    }


    private double updateDiscountByCartOffer(){
        if(cartOffer != null)
            discount += cartOffer.getDiscountByCartOffer(totalPrice, discount);
        return discount;
    }

    public void addToCart(Product product, int quantity) {
        if(product != null) {
            CartItem existingCartItem = findCartItem(product.getName());
            if (existingCartItem != null)
                existingCartItem.increaseQuantity(quantity);
            else {
                CartItem newCartItem = new CartItem(product, quantity);
                cartItems.add(newCartItem);
            }
            updateActualTotalPrice();
            updateDiscountByProductOffer();
            updateDiscountByCartOffer();
            updateSalesTax();
            updateTotalPrice();
            return;
        }
        throw new NullProductTypeException("product type is null", HttpStatus.BAD_REQUEST);
    }

    public double getTotal() {
        return grandTotal;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getDiscount(){
        return discount;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(cartItems);
    }

}
