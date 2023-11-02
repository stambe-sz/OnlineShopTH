package onlineshop.service;

import onlineshop.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    boolean create(OrderServiceModel inputOrder);

    OrderServiceModel updateById(long orderId);

    boolean deleteById(long orderId);

    OrderServiceModel findMyOrders(long userId);

    List<OrderServiceModel> getAll();
}
