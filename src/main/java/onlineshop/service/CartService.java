package onlineshop.service;

import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.ProductServiceModel;

import java.util.List;

public interface CartService {

    void addProductToCart(CartItemServiceModel cism) throws Exception;

    List<ProductServiceModel> getCartItems() throws Exception;
        /*
    void addProduct(CartItemServiceModel cartItemServiceModel);
    void updateProductQuantity(ProductServiceModel productServiceModel, int newQuantity);
    void removeProduct(ProductServiceModel productServiceModel);
    void clearCart();

    BigDecimal getTotalPrice();
    boolean cartCheckout(String username);
        */
}
