package com.project.ecommerce.DTO;

import com.project.ecommerce.Entity.Order;

import java.util.List;

public class OrderDTO {

    private int id;
    private Long createdAt;
    private String status;
    private double totalAmount;
    private List<OrderItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(int id, Long createdAt, String status,
                    double totalAmount, List<OrderItemDTO> items) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public OrderDTO convertToDTO(Order order) {

        List<OrderItemDTO> itemDTOs = order.getItems()
                .stream()
                .map(item -> new OrderItemDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPriceAtPurchase()
                ))
                .toList();

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setStatus(order.getStatus().name());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setItems(itemDTOs);

        return dto;
    }


    public int getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}

