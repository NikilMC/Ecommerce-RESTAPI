package com.project.ecommerce.Repository;

import com.project.ecommerce.Entity.Order;
import com.project.ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByUser(User user);
}
