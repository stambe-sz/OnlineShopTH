package onlineshop.service;

import onlineshop.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    boolean create(OrderServiceModel inputOrder);

    boolean updateById(long orderId);

    boolean updateByOrderNumber(String numberOfOrder);

    boolean deleteById(long orderId);

    List<OrderServiceModel> getAll();
}
