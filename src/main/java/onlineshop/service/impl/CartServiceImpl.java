package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.CartServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.service.CartService;
import onlineshop.service.ProductService;
import onlineshop.service.UserService;
import onlineshop.tools.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ProductService productService;
    private final Tools tools;

    @Override
    public void addProductToCart(CartItemServiceModel cism) throws Exception {
        UserServiceModel foundUser = userService.getUserByUsername(cism.getUsername());
        //ProductServiceModel foundProduct = productService.getProductById(cism.getProductId());
        if (foundUser.getCart() == null){
            foundUser.setCart(new CartServiceModel(
                    foundUser.getUsername(),
                    new ArrayList<>()
            ));
        }
        CartServiceModel userCart = foundUser.getCart();
        List<CartItemServiceModel> itemsInCart = userCart.getCartItems();
        itemsInCart.add(cism);
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
        ProductServiceModel pr = null;
        for (Map.Entry<Long, Integer> item: items.entrySet()) {
            pr = this.productService.getProductById(item.getKey());
            foundProducts.add(pr);
        }
        return foundProducts;
    }
}

