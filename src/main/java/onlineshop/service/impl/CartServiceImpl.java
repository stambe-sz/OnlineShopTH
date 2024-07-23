package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.CartItem;
import onlineshop.model.entity.User;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.CartServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.repository.CartRepository;
import onlineshop.repository.UserRepository;
import onlineshop.service.CartService;
import onlineshop.service.ProductService;
import onlineshop.service.UserService;
import onlineshop.tools.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final CartRepository cartRepository;
    private final Tools tools;

    @Override
    public void addProductToCart(Long productId) throws Exception {
        UserServiceModel foundUser = userService.getUserByUsername(tools.getLoggedUser());
        if (foundUser.getCart() == null){
            foundUser.setCart(new CartServiceModel(
                    foundUser.getUsername(),
                    new ArrayList<>()
            ));
        }
        CartServiceModel userCart = foundUser.getCart();
        List<CartItemServiceModel> itemsInCart = userCart.getCartItems();
        boolean isExist = itemsInCart.stream().anyMatch(e-> e.getProductId().equals(productId));
        if (isExist) {
            CartItemServiceModel cartItemSm = itemsInCart.stream()
                    .filter(e-> e.getProductId().equals(productId))
                    .findFirst().orElse(null);
            if(cartItemSm == null) throw new Exception("Server Error 500 :)");
            cartItemSm.setQuantity(cartItemSm.getQuantity() + 1);
        } else {
            itemsInCart.add(new CartItemServiceModel(tools.getLoggedUser(), productId, 1));
        }
        this.userService.saveUserToDb(foundUser);
    }

    @Override
    public List<ProductServiceModel> getCartItems() throws Exception {
        String loggedUser = tools.getLoggedUser();
        UserServiceModel foundUser = userService.getUserByUsername(loggedUser);
        List<CartItemServiceModel> cartList = foundUser.getCart().getCartItems();
        Map<Long, Integer> items = new HashMap<>();

        for (CartItemServiceModel currentItem : cartList) {
            if (items.containsKey(currentItem.getProductId())) {
                items.put(currentItem.getProductId(),
                        items.get(currentItem.getProductId()) + currentItem.getQuantity());
            } else {
                items.put(currentItem.getProductId(), currentItem.getQuantity());
            }
        }
        List<ProductServiceModel> foundProducts = new ArrayList<>();
        ProductServiceModel pr;
        for (Map.Entry<Long, Integer> item: items.entrySet()) {
            pr = this.productService.getProductById(item.getKey());
            pr.setOrderQuantity(item.getValue());
            foundProducts.add(pr);
        }
        return foundProducts;
    }


    public List<ProductServiceModel> getCartItems2() throws Exception {
        String loggedUser = tools.getLoggedUser();
        UserServiceModel foundUser = userService.getUserByUsername(loggedUser);
        List<CartItemServiceModel> cartList = foundUser.getCart().getCartItems();
        Map<Long, Integer> items = new HashMap<>();

        for (CartItemServiceModel currentItem : cartList) {
            if (items.containsKey(currentItem.getProductId())) {
                items.put(currentItem.getProductId(),
                        items.get(currentItem.getProductId()) + currentItem.getQuantity());
            } else {
                items.put(currentItem.getProductId(), currentItem.getQuantity());
            }
        }
        List<ProductServiceModel> foundProducts = new ArrayList<>();
        ProductServiceModel pr = null;

        for (Map.Entry<Long, Integer> item: items.entrySet()) {
            pr = this.productService.getProductById(item.getKey());
            pr.setOrderQuantity(item.getValue());
            foundProducts.add(pr);
        }
        return foundProducts;
    }

    @Override
    public void removeProductFromCart(Long productId) throws Exception {
        User foundUser = this.userRepository.findById(productId).orElse(null);
        if(foundUser == null) throw new Exception("User...");
        List<CartItem> cartItems = foundUser.getCart().getCartItems();
        cartItems = cartItems.stream().filter(e -> !e.getProductId().equals(productId))
                .toList();
        foundUser.getCart().setCartItems(cartItems);
        this.userRepository.saveAndFlush(foundUser);
    }
}
