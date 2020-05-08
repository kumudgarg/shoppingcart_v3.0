package com.thoughtworks.shoppingcart.repository;

import com.thoughtworks.shoppingcart.model.CartOffer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class CartOfferRepo {

    public static Map<UUID, CartOffer> cartMap = new HashMap<>();

    public CartOffer findByCartId(UUID id) {
        CartOffer cartOffer = cartMap.get(id);
        return cartOffer;
    }

}
