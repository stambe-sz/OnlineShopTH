package onlineshop.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Product;
import onlineshop.model.entity.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartServiceModel extends BaseServiceModel{
    private User user;
    private List<Product> products;
}
