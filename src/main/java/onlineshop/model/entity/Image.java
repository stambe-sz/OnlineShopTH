package onlineshop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image extends BaseEntity{
    @Column
    private String imageUrl;
    @Column
    private BigDecimal size;
    @Column
    private String description;
    @Column
    private String contentType;
    @ManyToOne
    private Product product;
}
