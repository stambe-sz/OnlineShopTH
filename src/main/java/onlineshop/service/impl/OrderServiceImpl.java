package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Order;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.OrderServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.repository.OrderRepository;
import onlineshop.service.OrderService;
import onlineshop.service.ProductService;
import onlineshop.service.UserService;
import onlineshop.tools.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final Tools tools;

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

    @Override
    public void finishOrder() throws Exception {
        String username = tools.getLoggedUser();
        UserServiceModel user = userService.getUserByUsername(username);
        List<CartItemServiceModel> userCart = user.getCart().getCartItems();
        userCart.forEach(e -> {
            ProductServiceModel foundProduct = productService.getProductById(e.getProductId());
            int newQuantity = foundProduct.getQuantity() - e.getQuantity();
            foundProduct.setQuantity(newQuantity);
            productService.editProduct(foundProduct);
        });
        List<String> productsIds = new ArrayList<>();
        userCart.forEach(p -> {
            productsIds.add(String.format("%s#%s",
                    p.getProductId(), p.getQuantity()));
        });
        OrderServiceModel newOrder = new OrderServiceModel(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "new",
                "",
                username,
                productsIds
        );
        this.create(newOrder);
        user.getCart().getCartItems().clear();
        userService.saveUserToDb(user);
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
