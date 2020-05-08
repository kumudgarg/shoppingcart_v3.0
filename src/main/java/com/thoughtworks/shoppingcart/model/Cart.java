package com.thoughtworks.shoppingcart.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.shoppingcart.dto.ProductDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Cart {

    private UUID id;

    private List<CartItem> cartItem;

    @JsonIgnore
    private CartOffer cartOffer;

    private double salesTax;

    private double discount;

    private double grandTotal;

    public UUID getId() {
        id = UUID.randomUUID();
        return id;
    }

}
