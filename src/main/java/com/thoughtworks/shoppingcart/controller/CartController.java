package com.thoughtworks.shoppingcart.controller;
import com.thoughtworks.shoppingcart.model.Product;
import com.thoughtworks.shoppingcart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart/manage-products")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity addToCart(@RequestParam(name = "quantity") int quantity, @Valid @RequestBody Product product){
        cartService.addToCart(product, quantity);
        return ResponseEntity.ok(null);
    }


}
