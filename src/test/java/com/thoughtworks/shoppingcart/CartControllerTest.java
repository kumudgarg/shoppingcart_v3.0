package com.thoughtworks.shoppingcart;

import com.thoughtworks.shoppingcart.controller.CartController;
import com.thoughtworks.shoppingcart.model.Product;
import com.thoughtworks.shoppingcart.services.CartService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.assertj.ApplicationContextAssert;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class CartControllerTest {

    private MockMvc mvc;

    @InjectMocks
    CartController cartController;

    @Mock
    CartService cartService;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(cartController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddedASingleProductIntoCartWhenARequestMakesToAddProduct() throws Exception {
        String jsonString = "[{\"product\":{\"name\":\"apple\",\"price\":0.99},\"quantity\":5}]";
        Product apple = new Product("apple", 0.99);
        Mockito.verify(cartService).addToCart(apple, 5);
        mvc.perform(MockMvcRequestBuilders.post("/cart/manage-products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("apple")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is("0.99")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is("5")));
    }

}

