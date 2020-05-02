package com.thoughtworks.shoppingcart;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();


    public void addToCart(Product product, int quantity) {
        CartItem existingCartItem = findCartItem(product.getName());
        if(existingCartItem != null)
            existingCartItem.increaseQuantity(quantity);
        else {
            CartItem newCartItem = new CartItem(product, quantity);
            cartItems.add(newCartItem);
        }
    }

    private CartItem findCartItem(String name) {
        return cartItems.stream().filter(cartItem -> cartItem.getName() == name).findFirst().orElse(null);
    }

    public double getTotal() {
        return 0;
    }
}
