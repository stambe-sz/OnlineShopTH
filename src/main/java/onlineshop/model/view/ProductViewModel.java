package onlineshop.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import onlineshop.model.entity.Category;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewModel extends BaseViewModel {
    private String name;
    private String description;
    private int quantity;
    private int orderQuantity;
    private double price;
    private Category category;
    private String productCondition;
    private List<String> images;
}
