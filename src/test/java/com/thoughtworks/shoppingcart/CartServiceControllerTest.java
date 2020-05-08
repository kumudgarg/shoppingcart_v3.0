/*
package com.thoughtworks.shoppingcart;

import com.thoughtworks.shoppingcart.Exception.NullProductTypeException;
import com.thoughtworks.shoppingcart.controller.CartController;
import com.thoughtworks.shoppingcart.model.Product;
import com.thoughtworks.shoppingcart.model.BuyXGetYOffer;
import com.thoughtworks.shoppingcart.model.CartOffer;
import com.thoughtworks.shoppingcart.services.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CartServiceControllerTest {

    private MockMvc mvc;

    @Mock
    CartService cartService;

    @InjectMocks
    CartController cartController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void shouldAddedASingleProductIntoCartWhenARequestMakesToAddProduct() throws Exception {
        Integer quantity = 5;
        String jsonString = "{\"name\":\"apple\",\"price\":0.99}";
        Product apple = new Product("apple", 0.99);
        doNothing().when(cartService).addToCart(apple, quantity);
        mvc.perform(MockMvcRequestBuilders.post("/cartService/manage-products")
                .param("quantity", quantity.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void shouldReturnTotalPriceWhenARequestMakesForGetTotalPriceOfAddedProducts() throws Exception {
        Integer quantity = 5;
        Product apple = new Product("apple", 0.99);
        doNothing().when(cartService).addToCart(apple, quantity);
        when(cartService.getTotal()).thenReturn(5.05);
        mvc.perform(MockMvcRequestBuilders.get("/cartService/manage-products/total")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnSalesTaxWhenARequestMakesForSalesTaxOfAddedProducts() throws Exception {
        Integer quantity = 5;
        Product apple = new Product("apple", 0.99);
        doNothing().when(cartService).addToCart(apple, quantity);
        when(cartService.getSalesTax()).thenReturn(0.01);
        mvc.perform(MockMvcRequestBuilders.get("/cartService/manage-products/salesTax")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnTotalDiscountWhenARequestMakesForDiscountOfAddedProducts() throws Exception {
        Integer quantity = 5;
        Product apple = new Product("apple", 0.99, new BuyXGetYOffer(2, 1));
        doNothing().when(cartService).addToCart(apple, quantity);
        when(cartService.getDiscount()).thenReturn(0.99);
        mvc.perform(MockMvcRequestBuilders.get("/cartService/manage-products/discount")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnTotalDiscountToHandleCartOffer() throws Exception {
        cartService = new CartService(new CartOffer(10, 1));
        cartService = mock(CartService.class);
        Product apple = new Product("apple", 0.99, new BuyXGetYOffer(2, 1));
        Product mask = new Product("mask", 1.99);
        doNothing().when(cartService).addToCart(apple, 10);
        doNothing().when(cartService).addToCart(mask, 3);
        when(cartService.getDiscount()).thenReturn(4.26);
        mvc.perform(MockMvcRequestBuilders.get("/cartService/manage-products/discount")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldThrowCustomExceptionWhenNullProductAddedToCart() throws Exception {
        doThrow(NullProductTypeException.class).when(cartService).addToCart(null, 10);
        mvc.perform(MockMvcRequestBuilders.post("/cartService/manage-products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



}

*/
