package com.thoughtworks.shoppingcart.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {

    private Long id;

    private int quantity;

    public ProductDTO(Long productId, int quanity) {
        this.id = productId;
        this.quantity = quanity;
    }
}
