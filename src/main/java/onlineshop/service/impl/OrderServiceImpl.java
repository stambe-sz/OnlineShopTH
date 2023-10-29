package onlineshop.service.impl;

import onlineshop.model.service.OrderServiceModel;
import onlineshop.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public boolean create(OrderServiceModel inputOrder) {
        return false;
    }

    @Override
    public boolean updateById(String orderId) {
        return false;
    }

    @Override
    public boolean updateByOrderNumber(String numberOfOrder) {
        return false;
    }

    @Override
    public boolean deleteById(String orderId) {
        return false;
    }

    @Override
    public List<OrderServiceModel> getAll() {
        return null;
    }
}
