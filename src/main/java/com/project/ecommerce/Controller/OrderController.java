package com.project.ecommerce.Controller;


import com.project.ecommerce.DTO.AddToCartRequest;
import com.project.ecommerce.DTO.CartDTO;
import com.project.ecommerce.DTO.CartMapper;
import com.project.ecommerce.DTO.OrderDTO;
import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.Order;
import com.project.ecommerce.Service.CartService;
import com.project.ecommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private OrderDTO orderDTO = new OrderDTO();

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderDTO> checkout(Authentication auth) {

        Order order = orderService.placeOrder(auth.getName());
        return ResponseEntity.ok(orderDTO.convertToDTO(order));
    }

    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> getMyOrders(Authentication auth) {

        List<Order> orders = orderService.getOrdersForUser(auth.getName());

        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (Order order : orders) {
            orderDTOs.add(orderDTO.convertToDTO(order));
        }
        return ResponseEntity.ok(orderDTOs);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(
            Authentication auth,
            @PathVariable int orderId){

        Order order = orderService.getOrderForUserById(auth.getName(), orderId);

        return ResponseEntity.ok(orderDTO.convertToDTO(order));
    }
}
