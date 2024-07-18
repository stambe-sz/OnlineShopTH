package onlineshop.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartServiceModel extends BaseServiceModel{
    private String username;
    private List<CartItemServiceModel> cartItems;
}
