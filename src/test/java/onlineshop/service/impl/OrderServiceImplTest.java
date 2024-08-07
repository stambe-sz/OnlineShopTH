package onlineshop.service.impl;

import onlineshop.model.entity.Order;
import onlineshop.model.service.OrderServiceModel;
import onlineshop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceImplTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void whenCreateValidOrder_thenExpectBoolTrue() {
        // arrange
        OrderServiceModel orderServiceModel = new OrderServiceModel();
        Order order = new Order();
        when(modelMapper.map(orderServiceModel, Order.class)).thenReturn(order);
        doNothing().when(orderRepository).save(order);

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, modelMapper);

        // act
        boolean result = orderService.create(orderServiceModel);

        // assert
        assertTrue(result);
    }

    @Test
    void whenUpdateValidOrder_thenExpectUpdatedOrder() {
        // arrange
        OrderServiceModel orderServiceModel = new OrderServiceModel();
        Order oldOrder = new Order();
        Order expectedOrder = new Order();
        orderServiceModel.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(oldOrder));
        when(modelMapper.map(orderServiceModel, Order.class)).thenReturn(expectedOrder);
        when(orderRepository.saveAndFlush(expectedOrder)).thenReturn(expectedOrder);
        when(modelMapper.map(expectedOrder, OrderServiceModel.class)).thenReturn(orderServiceModel);

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, modelMapper);

        // act
        OrderServiceModel result = orderService.update(orderServiceModel);

        // assert
        assertEquals(orderServiceModel, result);
    }


    @Test
    void whenDeleteValidOrder_thenExpectBoolTrue() {
        // arrange
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        doNothing().when(orderRepository).delete(order);

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, modelMapper);

        // act
        boolean result = orderService.deleteById(1L);

        // assert
        assertTrue(result);
    }

    @Test
    void whenDeleteNonExistingOrder_thenExpectException() {
        // arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, modelMapper);

        // act and assert
        assertThrows(NoSuchElementException.class, () -> orderService.deleteById(1L));
    }

    @Test
    void whenFindOrderOfValidUser_thenExpectOrderServiceModel() {
        // arrange
        String username = "username";
        Order order = new Order();
        OrderServiceModel expectedModel = new OrderServiceModel();
        when(orderRepository.findOrderByUserUsername(username)).thenReturn(Optional.of(order));
        when(modelMapper.map(order, OrderServiceModel.class)).thenReturn(expectedModel);

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, modelMapper);

        // act
        OrderServiceModel result = orderService.findMyOrders(username);

        // assert
        assertEquals(expectedModel, result);
    }

    @Test
    void whenFindOrderOfNonExistingUser_thenExpectException() {
        // arrange
        when(orderRepository.findOrderByUserUsername("username")).thenReturn(Optional.empty());

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, modelMapper);

        // act and assert
        assertThrows(NoSuchElementException.class, () -> orderService.findMyOrders("username"));
    }


    @Test
    void whenGetAll_thenExpectListOfOrderServiceModel() {
        // arrange
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orders = List.of(order1, order2);
        OrderServiceModel model1 = new OrderServiceModel();
        OrderServiceModel model2 = new OrderServiceModel();
        List<OrderServiceModel> expectedModels = List.of(model1, model2);
        when(orderRepository.findAll()).thenReturn(orders);
        when(modelMapper.map(order1, OrderServiceModel.class)).thenReturn(model1);
        when(modelMapper.map(order2, OrderServiceModel.class)).thenReturn(model2);

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, modelMapper);

        // act
        List<OrderServiceModel> result = orderService.getAll();

        // assert
        assertEquals(expectedModels, result);
    }

}



