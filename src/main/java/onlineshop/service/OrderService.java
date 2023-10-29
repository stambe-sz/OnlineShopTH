package onlineshop.service;

import onlineshop.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    boolean create(OrderServiceModel inputOrder);

    boolean updateById(String orderId);

    boolean updateByOrderNumber(String numberOfOrder);

    boolean deleteById(String orderId);

    List<OrderServiceModel> getAll();
}
