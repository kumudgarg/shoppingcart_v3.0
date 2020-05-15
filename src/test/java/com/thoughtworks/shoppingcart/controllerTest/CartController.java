package com.thoughtworks.shoppingcart.controllerTest;


import com.thoughtworks.shoppingcart.model.Cart;
import com.thoughtworks.shoppingcart.model.Product;
import com.thoughtworks.shoppingcart.services.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
public class CartController {


    private MockMvc mvc;

    @Mock
    CartService cartService;

    @InjectMocks
    CartController cartController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(cartController).build();
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void shouldReturnACartIdWhenNewCartGenerated() throws Exception {
        Cart cart  = new Cart(1l,0.0,0.0,0.0);
        String jsonString = "{\"id\": 1l,\"salesTax\":0.0,\"discount\":0.0,\"grandTotal\":0.0}";
        when(cartService.addCart(cart)).thenReturn(cart.getId());

        mvc.perform(MockMvcRequestBuilders.get("/my-onlineShopping-store/carts").
                accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"));
    }


}

//    @Test
//    public void shouldAddedASingleProductIntoCartWhenARequestMakesToAddProduct() throws Exception {
//        Integer quantity = 5;
//        String jsonString = "{\"name\":\"apple\",\"price\":0.99}";
//        Product apple = new Product("apple", 0.99);
//        doNothing().when(cartService).addToCart(apple, quantity);
//        mvc.perform(MockMvcRequestBuilders.post("/cartService/manage-products")
//                .param("quantity", quantity.toString())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonString)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }



