package onlineshop.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.CartItem;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CartItemServiceModel {
    private String username;
    private List<CartItem> cartItems;
}
