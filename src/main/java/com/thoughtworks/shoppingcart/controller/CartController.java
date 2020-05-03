package com.thoughtworks.shoppingcart.controller;

import com.thoughtworks.shoppingcart.model.Product;
import com.thoughtworks.shoppingcart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart/manage-products")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<HttpStatus> addToCart(@Valid @RequestBody Product product, @RequestParam int quantity){
        cartService.addToCart(product, quantity);
        return (ResponseEntity<HttpStatus>) ResponseEntity.ok();
    }


}
