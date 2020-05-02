package com.thoughtworks.shoppingcart;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.List;
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
        verify(cartItems).add(cartItem1);
        verify(cartItems).add(cartItem2);
    }

}
