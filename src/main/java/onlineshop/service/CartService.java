package onlineshop.service;

import onlineshop.model.entity.CartItem;
import onlineshop.model.entity.Product;
import onlineshop.model.service.ProductServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    void addProduct(ProductServiceModel productServiceModel, int quantity);
    void updateProductQuantity(ProductServiceModel productServiceModel, int newQuantity);
    void removeProduct(ProductServiceModel productServiceModel);
    void clearCart();
    List<CartItem> getCartItems();
    BigDecimal getTotalPrice();
}
