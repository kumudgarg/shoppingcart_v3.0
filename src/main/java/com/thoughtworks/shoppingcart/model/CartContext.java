package com.thoughtworks.shoppingcart.model;

import com.thoughtworks.shoppingcart.services.CartOffer;
import lombok.Data;

@Data
public class CartContext {

    private Product product;

    private CartOffer cartOffer;
}
