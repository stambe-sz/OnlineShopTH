package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Order;
import onlineshop.model.entity.Product;
import onlineshop.model.repository.OrderRepository;
import onlineshop.model.service.OrderServiceModel;
import onlineshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean create(OrderServiceModel orderServiceModel) {
        Order order = modelMapper.map(orderServiceModel,Order.class);
        this.orderRepository.save(order);
        this.findOrderById(order.getId());
        return true;
    }

    @Override
    public boolean updateById(long orderId) {
        return false;
    }

    @Override
    public boolean updateByOrderNumber(String numberOfOrder) {
        return false;
    }

    @Override
    public boolean deleteById(long orderId) {
        Order order = this.findOrderById(orderId);
        this.orderRepository.delete(order);
        return true;
    }
    //findMyOrders
    @Override
    public List<OrderServiceModel> getAll() {
        List<Order> foundOrders = this.orderRepository.findAll();
        return foundOrders.stream()
                .map(order -> modelMapper.map(order, OrderServiceModel.class))
                .collect(Collectors.toList());
    }
    private Order findOrderById(long orderId) {
        Order order = this.orderRepository.findById(orderId).orElse(null);
        if (order == null){
            throw new NoSuchElementException();
        }
        return order;
    }
}
