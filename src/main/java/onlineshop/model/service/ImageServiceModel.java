package onlineshop.model.service;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Product;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ImageServiceModel extends BaseServiceModel{
    private String imageUrl;
    private BigDecimal size;
    private String description;
    private String contentType;
    private Product product;
}
