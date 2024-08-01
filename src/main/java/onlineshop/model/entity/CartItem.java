package onlineshop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity{

    @Column
    private String username;
    @Column
    private Long productId;
    @Column
    private Double price;
    @Column
    private int quantity;
    @Column
    private String name;
}
