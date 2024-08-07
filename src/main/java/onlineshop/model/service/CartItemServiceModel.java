package onlineshop.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
