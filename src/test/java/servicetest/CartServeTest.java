package servicetest;

import com.thoughtworks.shoppingcart.dto.ProductDTO;
import com.thoughtworks.shoppingcart.model.*;
import com.thoughtworks.shoppingcart.repository.CartOfferRepo;
import com.thoughtworks.shoppingcart.repository.CartRepo;
import com.thoughtworks.shoppingcart.repository.ProductOfferRepo;
import com.thoughtworks.shoppingcart.repository.ProductRepo;
import com.thoughtworks.shoppingcart.services.CartService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class CartServeTest {

    @Mock
    private CartRepo cartRepo;

    @Mock
    private CartOfferRepo cartOfferRepo;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private ProductOfferRepo productOfferRepo;


    @InjectMocks
    private CartService cartService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnACartIdWhenGetACart() {
        Cart cart = new Cart();
        cart.setId(1l);
        Long cartId = cartService.addCart(cart);
        verify(cartRepo).save(cart);
        assertEquals(cart.getId(), cartId);

    }

    @Test
   public void shouldReturnProductIdWhenASingleProductAdded() {
      Product apple =  new Product(1l,"apple", 0.99);
      Long id = cartService.addProduct(apple);
      verify(productRepo).save(apple);
      assertEquals(apple.getId(), id);
    }

    @Test
    public void shouldReturnListOfProductOfferIdWhenListOfProductOffersAddedToProduct() {
            List<BuyXGetYOffer> buyXGetYOffers = new ArrayList<>();
            List<Long> list = new ArrayList<>();
            BuyXGetYOffer buyXGetYOffer = new BuyXGetYOffer(1l, 2, 1);
            buyXGetYOffers.add(buyXGetYOffer);
            List<Long> productOfferList = cartService.addProductOffer(buyXGetYOffers);
            for (int i = 0; i < buyXGetYOffers.size() ; i++) {
                verify(productOfferRepo).save(buyXGetYOffers.get(i));
                list.add(buyXGetYOffers.get(i).getId());
            }
            assertEquals(productOfferList.get(0), list.get(0));
    }

    @Test
    public void shouldReturnCartOfferIdWhenACartOfferAddedToCart() {
        List<CartOffer> cartOffers = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        CartOffer cartOffer = new CartOffer(1l, 10, 10);
        cartOffers.add(cartOffer);
        List<Long> cartOffersList = cartService.addCartOffer(cartOffers);
        for (int i = 0; i < cartOffers.size() ; i++) {
            verify(cartOfferRepo).save(cartOffers.get(i));
            list.add(cartOffers.get(i).getId());
        }
        assertEquals(cartOffersList.get(0), list.get(0));
    }

    @Test
    public void shouldVerifyIsProductOfferAddedToProductIdWhenAProductIdAndListOfOfferIdsGiven() {
        Product apple =  new Product(1l,"apple", 0.99);
        List<Long> list = new ArrayList<>();
        list.add(1l);
        when(productRepo.findById(apple.getId())).thenReturn(java.util.Optional.of(apple));
        List<BuyXGetYOffer> productOffers = new ArrayList<>();
        BuyXGetYOffer buyXGetYOffer = new BuyXGetYOffer(1l, 2, 1);
        for (int i = 0; i < list.size(); i++) {
            when(productOfferRepo.findById(list.get(i))).thenReturn(java.util.Optional.of(buyXGetYOffer));
            productOffers.add(buyXGetYOffer);
        }
        apple.setOffer(productOffers);
        cartService.addOfferToProduct(apple.getId(), list);
        verify(productRepo).save(apple);
    }

    @Test
    public void shouldVerifyIsCartOfferAddedToProductIdWhenACartIdAndListOfOfferIdsGiven() {
        Cart cart = new Cart();
        cart.setId(1l);
        List<Long> list = new ArrayList<>();
        list.add(1l);
        when(cartRepo.findById(cart.getId())).thenReturn(java.util.Optional.of(cart));
        List<CartOffer> cartOffers = new ArrayList<>();
        CartOffer cartOffer = new CartOffer(1l, 10, 10);
        for (int i = 0; i < list.size(); i++) {
            when(cartOfferRepo.findById(list.get(i))).thenReturn(java.util.Optional.of(cartOffer));
            cartOffers.add(cartOffer);
        }
        cart.setCartOffer(cartOffers);
        cartService.addOfferToCart(cart.getId(),list);
        verify(cartRepo).save(cart);
    }

    @Test
    public void shouldVerifyWetherProductIsAddedToCartOrNotWhenCartIdAndProductDtoReturns() {
        Cart cart = new Cart();
        cart.setId(1l);
        Long cartId = cartService.addCart(cart);
        Product apple =  new Product(1l,"apple", 0.99);
        Long productId = cartService.addProduct(apple);
        List<Long> list = new ArrayList<>();
        list.add(1l);
        when(productRepo.findById(apple.getId())).thenReturn(java.util.Optional.of(apple));
        List<BuyXGetYOffer> productOffers = new ArrayList<>();
        BuyXGetYOffer buyXGetYOffer = new BuyXGetYOffer(1l, 2, 1);
        for (int i = 0; i < list.size(); i++) {
            when(productOfferRepo.findById(list.get(i))).thenReturn(java.util.Optional.of(buyXGetYOffer));
            productOffers.add(buyXGetYOffer);
        }
        apple.setOffer(productOffers);
        cartService.addOfferToProduct(apple.getId(), list);

        List<Long> list1 = new ArrayList<>();
        list1.add(1l);
        when(cartRepo.findById(cart.getId())).thenReturn(java.util.Optional.of(cart));
        List<CartOffer> cartOffers = new ArrayList<>();
        CartOffer cartOffer = new CartOffer(1l, 10, 10);
        for (int i = 0; i < list1.size(); i++) {
            when(cartOfferRepo.findById(list1.get(i))).thenReturn(java.util.Optional.of(cartOffer));
            cartOffers.add(cartOffer);
        }
        cart.setCartOffer(cartOffers);
        cartService.addOfferToCart(cart.getId(),list);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = mock(CartItem.class);
        List<ProductDTO> productDTOS = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO(productId,5);
        productDTOS.add(productDTO);
        when(cartRepo.findById(cartId)).thenReturn(java.util.Optional.of(cart));
        for (ProductDTO productDTO1: productDTOS) {
            when(productRepo.findById(productDTO1.getId())).thenReturn(java.util.Optional.of(apple));
            int quanity = productDTO1.getQuantity();
            cartItem.setProduct(apple);
            cartItem.setQuantity(quanity);
            cartItems.add(cartItem);
        }
        cartService.addProduct(cartId, productDTOS);
        double total = cartService.getTotal();
        assertEquals(4.04, total,0.01);


    }
}
