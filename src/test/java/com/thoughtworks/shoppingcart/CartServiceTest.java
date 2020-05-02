package com.thoughtworks.shoppingcart;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CartServiceTest {


    @InjectMocks
    CartService cartService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddASingleProductWhenARequestMakesToAddedAProductIntoCart() {
        List<CartItem> cartItems = mock(List.class);
        CartItem cartItem = mock(CartItem.class);
        int quantity = 3;
        Product apple = new Product("apple", 1);
        cartItems.add(cartItem);
        cartService.addToCart(apple, quantity);
        verify(cartItems).add(cartItem);
    }

    @Test
    public void shouldAddMultipleProductWhenARequestMakesToAddedAProductIntoCart() {
        List<CartItem> cartItems = mock(List.class);
        CartItem cartItem1 = mock(CartItem.class);
        CartItem cartItem2 = mock(CartItem.class);
        int quantity = 3;
        Product apple = new Product("apple", 0.99);
        Product mask = new Product("mask", 1.99);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cartService.addToCart(apple, quantity);
        cartService.addToCart(mask,quantity);
        verify(cartItems).add(cartItem1);
        verify(cartItems).add(cartItem2);
    }
    @Test
    public void shouldHaveContentAndTotalWhenASingleProductsAddedIntoCart() {
        List<CartItem> cartItems = mock(List.class);
        CartItem cartItem = mock(CartItem.class);
        Product apple = new Product("apple", 0.99);
        cartItems.add(cartItem);
        cartService.addToCart(apple,5);
        double total = cartService.getTotal();
        assertEquals(4.95,total, 0.01);
        assertEquals("[{\"product\":{\"name\":\"apple\",\"price\":0.99},\"quantity\":5}]", cartService.toString());
    }

    @Test
    public void shouldReturnTotalPriceAndSalesTaxWhenMultipleProductAdded() {
        List<CartItem> cartItems = mock(List.class);
        CartItem cartItem1 = mock(CartItem.class);
        CartItem cartItem2 = mock(CartItem.class);
        Product apple = new Product("apple", 0.99);
        Product mask = new Product("mask", 1.99);
        cartItems.add(cartItem1);
        cartService.addToCart(apple,2);
        cartService.addToCart(apple,1);
        cartItems.add(cartItem2);
        cartService.addToCart(mask,3);
        assertEquals(0.18,cartService.getSalesTax(), 0.01);
        assertEquals(8.94,cartService.getTotal(), 0.01);
        assertEquals(9.12, cartService.getTotalWithSalesTax(), 0.01);
        assertEquals("[{\"product\":{\"name\":\"apple\",\"price\":0.99},\"quantity\":3},{\"product\":{\"name\":\"mask\",\"price\":1.99},\"quantity\":3}]", cartService.toString());

    }





}
