package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.CartItem;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ModelMapper modelMapper;

    @Override
    public void addProduct(CartItemServiceModel cartItemServiceModel) {
//        UserServiceModel foundUser = this.userService.getUserByUsername(
//                cartItemServiceModel.getUsername());
//        foundUser.getCart().getCartItems().add(cartItemServiceModel);
//        User user = this.modelMapper.map(foundUser, User.class);
//        this.userService.saveUserToDb(user);
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
