package onlineshop.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderServiceModel {
    private String numberOfOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private String note;
    private List<Product> products;
}
