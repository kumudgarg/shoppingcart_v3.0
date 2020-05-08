package com.thoughtworks.shoppingcart.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {

    private UUID id;

    private int qunatity;
}
