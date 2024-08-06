package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.CartServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.repository.CartRepository;
import onlineshop.repository.UserRepository;
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
import java.util.stream.Collectors;

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
        if (foundUser.getCart() == null) {
            foundUser.setCart(new CartServiceModel(
                    foundUser.getUsername(),
                    new ArrayList<>()
            ));
        }
        CartServiceModel userCart = foundUser.getCart();
        List<CartItemServiceModel> itemsInCart = userCart.getCartItems();
        boolean isExist = itemsInCart.stream().anyMatch(e -> e.getProductId().equals(productId));
        if (isExist) {
            CartItemServiceModel cartItemSm = itemsInCart.stream()
                    .filter(e -> e.getProductId().equals(productId))
                    .findFirst().orElse(null);
            if (cartItemSm == null) throw new Exception("Server Error 500 :)");
            cartItemSm.setQuantity(cartItemSm.getQuantity() + 1);
        } else {
            itemsInCart.add(new CartItemServiceModel(tools.getLoggedUser(), productId, 0.0, 1 ,"kafe"));
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
        for (Map.Entry<Long, Integer> item : items.entrySet()) {
            pr = this.productService.getProductById(item.getKey());
            pr.setOrderQuantity(item.getValue());
            foundProducts.add(pr);
        }
        if (foundProducts.isEmpty()){
            foundUser.setCart(null);
            userService.saveUserToDb(foundUser);
        }
        return foundProducts;
    }

    @Deprecated
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

        for (Map.Entry<Long, Integer> item : items.entrySet()) {
            pr = this.productService.getProductById(item.getKey());
            pr.setOrderQuantity(item.getValue());
            foundProducts.add(pr);
        }
        return foundProducts;
    }

    @Override
    public void removeProductFromCart(Long productId) throws Exception {
        UserServiceModel foundUser = this.userService.getUserByUsername(tools.getLoggedUser());
        if (foundUser == null) throw new Exception("User not found");
        List<CartItemServiceModel> cartItems = foundUser.getCart().getCartItems();

        for (CartItemServiceModel item : cartItems) {

            if (item.getProductId().equals(productId)) {
                if (item.getQuantity() >= 2) {
                    item.setQuantity(item.getQuantity() - 1);
                    foundUser.getCart().setCartItems(cartItems);
                } else {
                    cartItems.remove(item);
                    break;
                }
            }
        }
        this.userService.saveUserToDb(foundUser);
    }

    @Override
    public List<ProductViewModel> getUserCartItems(String username) throws Exception {
        UserServiceModel foundUser = this.userService.getUserByUsername(username);
        if (foundUser == null) throw new Exception("User not found");
        List<CartItemServiceModel> cartItems = foundUser.getCart().getCartItems();
        List<ProductViewModel> cartViewItems = cartItems
                .stream()
                .map(p -> {
                    ProductViewModel viewModel = new ProductViewModel();
                    viewModel.setName(p.getName());
                    viewModel.setQuantity(p.getQuantity());
                    viewModel.setPrice(p.getPrice());
                    viewModel.setId(p.getId());
                    return viewModel;
                }).collect(Collectors.toList());



        return cartViewItems;
    }


}
