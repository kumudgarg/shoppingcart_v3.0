package com.thoughtworks.shoppingcart.controller;
import com.thoughtworks.shoppingcart.dto.ProductDTO;
import com.thoughtworks.shoppingcart.model.Cart;
import com.thoughtworks.shoppingcart.model.Product;
import com.thoughtworks.shoppingcart.model.BuyXGetYOffer;
import com.thoughtworks.shoppingcart.repository.CartRepo;
import com.thoughtworks.shoppingcart.services.CartService;
import com.thoughtworks.shoppingcart.model.CartOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/my-onlineShopping-store")
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping("/carts")
    public  ResponseEntity<Long> getCart(){
        Cart cart = new Cart();
        Long id = cartService.addCart(cart);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/products")
    public  ResponseEntity<Long> addProduct(@RequestBody Product product){
        Long id = cartService.addProduct(product);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/offer/buy-x-get-y")
    public  ResponseEntity<List<Long>> addProductOffer(@RequestBody List<BuyXGetYOffer> buyXGetYOffer){
        List<Long> list = cartService.addProductOffer(buyXGetYOffer);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/offer/price-offer")
    public  ResponseEntity<List<Long>> addCartOffer(@RequestBody List<CartOffer> cartOffer){
        List<Long> list = cartService.addCartOffer(cartOffer);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/products/{productId}/offers")
    public  ResponseEntity addProductOfferToCart(@PathVariable Long productId, @RequestBody List<Long> offerId){
        cartService.addOfferToProduct(productId, offerId);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/carts/{cartId}/offers")
    public  ResponseEntity addCartOfferToCart(@PathVariable Long cartId, @RequestBody List<Long> offerId){
        cartService.addOfferToCart(cartId, offerId);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/carts/{cartId}/products")
    public ResponseEntity addToCart(@PathVariable Long cartId, @RequestBody List<ProductDTO> productDTOS){
        cartService.addProduct(cartId,productDTOS);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/carts/total")
    public ResponseEntity<Double> getTotal(){
        double total = cartService.getTotal();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/salesTax")
    public ResponseEntity<Double> getSalesTax(){
        double salesTax = cartService.getSalesTax();
        return ResponseEntity.ok(salesTax);
    }

    @GetMapping("/discount")
    public ResponseEntity<Double> getDiscount(){
        double discount = cartService.getDiscount();
        return ResponseEntity.ok(discount);
    }

    @GetMapping("carts/{cartId}")
    public ResponseEntity<Cart> getCartContents(@PathVariable Long cartId){
        Cart cartContents = cartService.getCartContents(cartId);
        return ResponseEntity.ok(cartContents);
    }


}
