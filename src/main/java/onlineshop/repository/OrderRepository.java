package onlineshop.repository;

import onlineshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAll();
    Optional<Order> findOrderByUserId(long user_id);
}
