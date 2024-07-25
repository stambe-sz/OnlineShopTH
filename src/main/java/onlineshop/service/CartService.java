package onlineshop.service;

import onlineshop.model.entity.Cart;
import onlineshop.model.entity.CartItem;
import onlineshop.model.entity.Product;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    void addProductToCart(Long productId) throws Exception;

    List<ProductServiceModel> getCartItems() throws Exception;

    void removeProductFromCart(Long productId) throws Exception;

    List<ProductViewModel> getUserCartItems(String username) throws Exception;
        /*
    void addProduct(CartItemServiceModel cartItemServiceModel);
    void updateProductQuantity(ProductServiceModel productServiceModel, int newQuantity);
    void removeProduct(ProductServiceModel productServiceModel);
    void clearCart();

    BigDecimal getTotalPrice();
    boolean cartCheckout(String username);
        */
}
