package onlineshop.service;

import onlineshop.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    boolean create(OrderServiceModel inputOrder);

    OrderServiceModel update(OrderServiceModel orderServiceModel);

    boolean deleteById(Long orderId);

    OrderServiceModel findMyOrders(String username);

    List<OrderServiceModel> getAll();

    void finishOrder() throws Exception;
}
