package onlineshop.model.view;

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
public class OrderAdminViewModel {
    private String username;
    private Double totalAmount;
    private List<CartItem> cartItems;
}
