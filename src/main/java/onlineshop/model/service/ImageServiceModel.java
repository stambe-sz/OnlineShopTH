package onlineshop.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Product;

@Getter
@Setter
@NoArgsConstructor
public class ImageServiceModel extends BaseServiceModel{
    private String imageUrl;
    private Product product;
}
