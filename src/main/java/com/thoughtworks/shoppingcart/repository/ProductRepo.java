package com.thoughtworks.shoppingcart.repository;

import com.thoughtworks.shoppingcart.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductRepo {

    public static Map<UUID, Product> productMap = new HashMap<>();

    public Product findByProductId(UUID id) {
        Product product = productMap.get(id);
        return product;
    }
}
