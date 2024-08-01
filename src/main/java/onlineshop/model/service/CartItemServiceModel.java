package onlineshop.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.CartItem;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemServiceModel extends BaseServiceModel {

	private String username;
    private Long productId;
    private Double price;
    private int quantity;
    private String name;
}
