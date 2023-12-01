package onlineshop.service;

import onlineshop.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    boolean create(OrderServiceModel inputOrder);

    OrderServiceModel update(OrderServiceModel ods);

    boolean deleteById(Long orderId);

    OrderServiceModel findMyOrders(Long userId);

    List<OrderServiceModel> getAll();
}
