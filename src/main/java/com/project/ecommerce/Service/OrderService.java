package com.project.ecommerce.Service;

import com.project.ecommerce.DTO.OrderDTO;
import com.project.ecommerce.Entity.*;
import com.project.ecommerce.Repository.*;
import com.project.ecommerce.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(UserRepository userRepository,
                        CartRepository cartRepository,
                        OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order placeOrder(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(Optional.ofNullable(user))
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(System.currentTimeMillis());
        order.setStatus(OrderStatus.PLACED);

        double totalAmount = 0;

        for (CartItem cartItem : cart.getCartItems()) {

            Product product = cartItem.getProduct();
            int requestedQty = cartItem.getQuantity();

            if (product.getQuantity() < requestedQty) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + product.getName()
                );
            }

            product.setQuantity(product.getQuantity() - requestedQty);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(requestedQty);
            orderItem.setPriceAtPurchase(product.getPrice());

            order.getItems().add(orderItem);

            totalAmount += product.getPrice() * requestedQty;
        }

        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        cart.getCartItems().clear();

        return savedOrder;
    }

    public List<Order> getOrdersForUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUser(user);
    }

    public Order getOrderForUserById(String email, int orderId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByIdAndUser(orderId, user)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

}