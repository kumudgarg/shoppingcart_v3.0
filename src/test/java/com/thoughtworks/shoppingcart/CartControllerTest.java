package com.thoughtworks.shoppingcart;

import com.thoughtworks.shoppingcart.controller.CartController;
import com.thoughtworks.shoppingcart.model.Product;
import com.thoughtworks.shoppingcart.services.CartItem;
import com.thoughtworks.shoppingcart.services.CartService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;

public class CartControllerTest {

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
        mvc.perform(MockMvcRequestBuilders.post("/cart/manage-products/Hello")
                .param("quantity", quantity.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

