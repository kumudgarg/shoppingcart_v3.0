package com.thoughtworks.shoppingcart;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CartService cartService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllProductsWhenARequestMakesToGetAllProducts() {
        List<Product> products = new ArrayList<>();
        Product apple = new Product("apple", 3);
        Product mask = new Product("mask", 3);
        products.add(apple);
        products.add(mask);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> productList = cartService.getAllProducts();
        assertEquals(products.size(), productList.size());
    }

    @Test
    public void shouldAddASingleProductWhenARequestMakesToAddedAProductIntoCart() {
        Product apple = new Product("apple", 1);
        cartService.addToCart(apple, 3);
        verify(productRepository).save(apple);

    }
}
