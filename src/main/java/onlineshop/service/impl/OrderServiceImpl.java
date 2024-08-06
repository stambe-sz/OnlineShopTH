package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Order;
import onlineshop.model.service.OrderServiceModel;
import onlineshop.repository.OrderRepository;
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
        return true;
    }

    @Override
    public OrderServiceModel update(OrderServiceModel orderServiceModel) {
        Order order = this.orderRepository.findById(orderServiceModel.getId()).orElse(null);
        this.modelMapper.map(orderServiceModel, order);
        assert order != null;
        this.orderRepository.saveAndFlush(order);
        return modelMapper.map(order, OrderServiceModel.class);
    }
    @Override
    public boolean deleteById(Long orderId) {
        Order order = this.findOrderById(orderId);
        this.orderRepository.delete(order);
        return true;
    }
    @Override
    public OrderServiceModel findMyOrders(String username){
        Order order = this.orderRepository.findOrderByUserUsername(username).orElse(null);
        if (order == null){
            throw new NoSuchElementException();
        }
        return modelMapper.map(order, OrderServiceModel.class);
    }

    @Override
    public List<OrderServiceModel> getAll() {
        List<Order> foundOrders = this.orderRepository.findAll();
        return foundOrders.stream()
                .map(order -> modelMapper.map(order, OrderServiceModel.class))
                .collect(Collectors.toList());
    }
    private Order findOrderById(Long orderId) {
        Order order = this.orderRepository.findById(orderId).orElse(null);
        if (order == null){
            throw new NoSuchElementException();
            //todo change exception
        }
        return order;
    }
}
