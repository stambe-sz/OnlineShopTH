package onlineshop.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartServiceModel extends BaseServiceModel{
    private String username;
    private List<CartItemServiceModel> cartItems;
}
