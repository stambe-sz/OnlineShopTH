package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.CartItem;
import onlineshop.model.entity.User;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.repository.UserRepository;
import onlineshop.service.CartService;
import onlineshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addProduct(CartItemServiceModel cartItemServiceModel) {
        UserServiceModel foundUser = this.userService.getUserByUsername(
                cartItemServiceModel.getUsername());
        foundUser.getCart().getCartItems().add(cartItemServiceModel);
        User u = this.modelMapper.map(foundUser, User.class);
        this.userRepository.saveAndFlush(u);
    }

    @Override
    public void updateProductQuantity(ProductServiceModel productServiceModel, int newQuantity) {

    }

    @Override
    public void removeProduct(ProductServiceModel productServiceModel) {

    }

    @Override
    public void clearCart() {

    }

    @Override
    public List<CartItem> getCartItems() {
        return null;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public boolean cartCheckout(String username) {
        //find user
        //take a cart
        //create object with proper fields for order
        //LocalDateTime.now()
        //new Order(..........)
        //decrease quantities for every product
        //save every product
        //remove all elements from the list of Cart
        //save user
        return false;
    }
}
