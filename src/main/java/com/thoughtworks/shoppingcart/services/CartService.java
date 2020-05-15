package com.thoughtworks.shoppingcart.services;

import com.google.gson.Gson;
import com.thoughtworks.shoppingcart.Exception.NullProductTypeException;
import com.thoughtworks.shoppingcart.dto.ProductDTO;
import com.thoughtworks.shoppingcart.model.*;
import com.thoughtworks.shoppingcart.repository.CartOfferRepo;
import com.thoughtworks.shoppingcart.repository.CartRepo;
import com.thoughtworks.shoppingcart.repository.ProductOfferRepo;
import com.thoughtworks.shoppingcart.repository.ProductRepo;
import com.thoughtworks.shoppingcart.utility.MoneyUtility;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;


@Data
@Service
public class CartService {



    private List<CartItem> cartItems = new ArrayList<>();

    private double totalPrice;

    private double salesTax;

    private double discount;

    private double grandTotal;

    private Cart cart;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    private CartOffer cartOffer;

    @Autowired
    private ProductOfferRepo productOfferRepo;

    @Autowired
    private CartOfferRepo cartOfferRepo;


    public CartService() {
        cartOffer = null;
    }

    private CartItem findCartItem(String name) {
        return cartItems.stream().filter(cartItem -> cartItem.getName() == name).findFirst().orElse(null);
    }

    private double updateActualTotalPrice() {
        totalPrice = cartItems.stream().mapToDouble(cartItem -> cartItem.getPrice()).sum();
        return totalPrice;
    }

    private double updateSalesTax() {
        salesTax = MoneyUtility.getSalesTax(totalPrice - discount);
        return MoneyUtility.format(salesTax);
    }

    private double updateTotalPrice() {
        return grandTotal = totalPrice + salesTax - discount;
    }


    private double updateDiscountByProductOffer() {
        discount = cartItems.stream().mapToDouble(CartItem::getDiscount).sum();
        return discount;
    }


    private double updateDiscountByCartOffer() {
        if (cartOffer != null)
            discount += cartOffer.getDiscountByCartOffer(totalPrice, discount);
        return discount;
    }

    public Long addCart(Cart cart) {
        cartRepo.save(cart);
        Long id = cart.getId();
        return id;
    }

    public Long addProduct(Product product) {
        productRepo.save(product);
        Long id = product.getId();
        return id;
    }


    public List<Long> addProductOffer(List<BuyXGetYOffer> buyXGetYOffer) {

        List<Long> list = new ArrayList<>();
        for (int i = 0; i < buyXGetYOffer.size(); i++) {
            productOfferRepo.save(buyXGetYOffer.get(i));
            list.add(buyXGetYOffer.get(i).getId());
        }
        return list;
    }

    public List<Long> addCartOffer(List<CartOffer> cartOffer) {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < cartOffer.size(); i++) {
            cartOfferRepo.save(cartOffer.get(i));
            list.add(cartOffer.get(i).getId());
        }
        return list;
    }

    public void addOfferToProduct(Long productId, List<Long> offerIds) {
        Optional<Product> product = productRepo.findById(productId);
        List<BuyXGetYOffer> offer =  new ArrayList<>();
        for (int i = 0; i < offerIds.size() ; i++) {
            Optional<BuyXGetYOffer> buyXGetYOffer = productOfferRepo.findById(offerIds.get(i));
            offer.add(buyXGetYOffer.get());
        }
        product.get().setOffer(offer);
        productRepo.save(product.get());
    }

    public void addOfferToCart(Long cartId, List<Long> offerId) {
        Optional<Cart> cart = cartRepo.findById(cartId);
        List<CartOffer> offer =  new ArrayList<>();
        for (int i = 0; i < offerId.size() ; i++) {
            Optional<CartOffer> cartOffer = cartOfferRepo.findById(offerId.get(i));
            offer.add(cartOffer.get());
        }
        cart.get().setCartOffer(offer);
        cartRepo.save(cart.get());
    }

    public void addProduct(Long cartId, List<ProductDTO> productDTOS) {
        Optional<Cart> cart = cartRepo.findById(cartId);
        for (ProductDTO dto: productDTOS) {
            Optional<Product> product = productRepo.findById(dto.getId());
            int quantity = dto.getQuantity();
            if (product.get() == null)
                throw new NullProductTypeException("product type is null", HttpStatus.BAD_REQUEST);
            CartItem existingCartItem = findCartItem(product.get().getName());
            //int existingQuantity = dto.getQuantity();
            if (existingCartItem != null) {
                if(existingCartItem.getQuantity() != quantity) {
                    cartItems.remove(existingCartItem);
                    existingCartItem.increaseQuantity(quantity);
                    cartItems.add(existingCartItem);
                    //cart.setCartItem(cartItems);
                }

            }
            else {
                CartItem newCartItem = new CartItem(product.get(), quantity);
                cartItems.add(newCartItem);
                //cart.setCartItem(cartItems);
            }
            updateActualTotalPrice();
            updateDiscountByProductOffer();
            updateDiscountByCartOffer();
            updateSalesTax();
            updateTotalPrice();
        }

    }

    public double getTotal() {
        return grandTotal;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getDiscount() {
        return discount;
    }


    public Cart getCartContents(Long cartId) {
        Optional<Cart> cart = cartRepo.findById(cartId);
        cart.get().setGrandTotal(grandTotal);
        cart.get().setSalesTax(salesTax);
        cart.get().setDiscount(discount);
        cartRepo.save(cart.get());
        cartItems.clear();
        return cart.get();
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(cartItems);
    }
}
