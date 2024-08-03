package onlineshop.service.impl;

import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.CartServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.repository.UserRepository;
import onlineshop.service.CartService;
import onlineshop.service.ProductService;
import onlineshop.service.UserService;
import onlineshop.tools.Tools;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CartServiceImplTest {

    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ProductService productService;
    @MockBean
    private Tools tools;

    @Test
    void testGetCartItems() throws Exception {

        UserServiceModel userServiceModel = new UserServiceModel();
        CartServiceModel cartServiceModel = new CartServiceModel();
        List<CartItemServiceModel> cartItemServiceModels = new ArrayList<>();
        CartItemServiceModel cartItemServiceModel = new CartItemServiceModel();
        cartItemServiceModel.setProductId(1L);
        cartItemServiceModels.add(cartItemServiceModel);
        cartServiceModel.setCartItems(cartItemServiceModels);
        userServiceModel.setCart(cartServiceModel);

        when(tools.getLoggedUser()).thenReturn("admin");
        when(userService.getUserByUsername(any(String.class))).thenReturn(userServiceModel);

        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setOrderQuantity(1);
        when(productService.getProductById(any(Long.class))).thenReturn(productServiceModel);
        CartService cartService = new CartServiceImpl(modelMapper, userService, userRepository, productService, null, tools);
        List<ProductServiceModel> result = cartService.getCartItems();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getOrderQuantity());
    }
    @Test
    void testAddProductToCart() throws Exception {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("admin");
        CartServiceModel cartServiceModel = new CartServiceModel();
        userServiceModel.setCart(cartServiceModel);

        when(tools.getLoggedUser()).thenReturn("admin");
        when(userService.getUserByUsername(any(String.class))).thenReturn(userServiceModel);

        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setId(1L);
        when(productService.getProductById(any(Long.class))).thenReturn(productServiceModel);
        CartService cartService = new CartServiceImpl(modelMapper, userService, userRepository, productService, null, tools);

        cartService.addProductToCart(1L);
        verify(userService, times(1)).saveUserToDb(userServiceModel);
    }

    @Test
    void testAddProductToCartExistingProduct() throws Exception {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("admin");
        CartServiceModel cartServiceModel = new CartServiceModel();
        List<CartItemServiceModel> cartItemServiceModels = new ArrayList<>();
        CartItemServiceModel cartItemServiceModel = new CartItemServiceModel();
        cartItemServiceModel.setProductId(1L);
        cartItemServiceModel.setQuantity(1);
        cartItemServiceModels.add(cartItemServiceModel);
        cartServiceModel.setCartItems(cartItemServiceModels);
        userServiceModel.setCart(cartServiceModel);

        when(tools.getLoggedUser()).thenReturn("admin");
        when(userService.getUserByUsername(any(String.class))).thenReturn(userServiceModel);

        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setId(1L);
        when(productService.getProductById(any(Long.class))).thenReturn(productServiceModel);

      CartService cartService = new CartServiceImpl(modelMapper, userService, userRepository, productService, null, tools);

      cartService.addProductToCart(1L);
      assertEquals(2, userServiceModel.getCart().getCartItems().get(0).getQuantity());
      verify(userService, times(1)).saveUserToDb(userServiceModel);
   }

    @Test
    void testRemoveProductFromCartNonExistingProduct() throws Exception {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("admin");
        CartServiceModel cartServiceModel = new CartServiceModel();
        userServiceModel.setCart(cartServiceModel);

        when(tools.getLoggedUser()).thenReturn("admin");
        when(userService.getUserByUsername(any(String.class))).thenReturn(userServiceModel);

        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setId(1L);
        when(productService.getProductById(any(Long.class))).thenReturn(productServiceModel);
        CartService cartService = new CartServiceImpl(modelMapper, userService, userRepository, productService, null, tools);

        cartService.removeProductFromCart(1L);
        verify(userService, times(1)).saveUserToDb(userServiceModel);
        assertEquals(0, userServiceModel.getCart().getCartItems().size());
    }

    @Test
    void testRemoveProductFromCartExistingProduct() throws Exception {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("admin");
        CartServiceModel cartServiceModel = new CartServiceModel();
        List<CartItemServiceModel> cartItemServiceModels = new ArrayList<>();
        CartItemServiceModel cartItemServiceModel = new CartItemServiceModel();
        cartItemServiceModel.setProductId(1L);
        cartItemServiceModel.setQuantity(2);
        cartItemServiceModels.add(cartItemServiceModel);
        cartServiceModel.setCartItems(cartItemServiceModels);
        userServiceModel.setCart(cartServiceModel);

        when(tools.getLoggedUser()).thenReturn("admin");
        when(userService.getUserByUsername(any(String.class))).thenReturn(userServiceModel);

        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setId(1L);
        when(productService.getProductById(any(Long.class))).thenReturn(productServiceModel);
        CartService cartService = new CartServiceImpl(modelMapper, userService, userRepository, productService, null, tools);

        cartService.removeProductFromCart(1L);
        verify(userService, times(1)).saveUserToDb(userServiceModel);
        assertEquals(1, userServiceModel.getCart().getCartItems().get(0).getQuantity());
    }


    @Test
    void testGetUserCartItems() throws Exception {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("admin");
        CartServiceModel cartServiceModel = new CartServiceModel();
        List<CartItemServiceModel> cartItemServiceModels = new ArrayList<>();
        CartItemServiceModel cartItemServiceModel = new CartItemServiceModel();
        cartItemServiceModel.setProductId(1L);
        cartItemServiceModel.setName("testProduct");
        cartItemServiceModel.setQuantity(2);
        cartItemServiceModels.add(cartItemServiceModel);
        cartServiceModel.setCartItems(cartItemServiceModels);
        userServiceModel.setCart(cartServiceModel);

        when(userService.getUserByUsername(any(String.class))).thenReturn(userServiceModel);

        CartService cartService = new CartServiceImpl(modelMapper, userService, userRepository, productService, null, tools);

        List<ProductViewModel> result = cartService.getUserCartItems("admin");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testProduct", result.get(0).getName());
        assertEquals(2, result.get(0).getQuantity());
    }

    @Test
    void testGetUserCartItems_noUserFound() throws Exception {
        when(userService.getUserByUsername(any(String.class))).thenReturn(null);

        CartService cartService = new CartServiceImpl(modelMapper, userService, userRepository, productService, null, tools);

        Exception exception = assertThrows(Exception.class, () -> {
            cartService.getUserCartItems("admin");
        });

        assertEquals("User not found", exception.getMessage());
    }
}


