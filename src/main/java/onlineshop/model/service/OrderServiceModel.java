package onlineshop.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceModel extends BaseServiceModel {
    private String numberOfOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private String note;
    private String userUsername;
    private List<String> productIds;
}
