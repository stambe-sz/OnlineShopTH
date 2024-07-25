package onlineshop.model.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemBindingModel {

    private String username;
    private Long productId;
    private Double price;
    private int quantity;
}
