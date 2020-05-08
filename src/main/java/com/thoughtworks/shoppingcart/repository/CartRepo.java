package com.thoughtworks.shoppingcart.repository;

import com.thoughtworks.shoppingcart.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class CartRepo {

    public static Map<UUID, Cart> cartMap = new HashMap<>();

    public Cart findByCartId(UUID id) {
        Cart cart = cartMap.get(id);
        return cart;
    }
}
