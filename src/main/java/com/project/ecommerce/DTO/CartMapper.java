package com.project.ecommerce.DTO;

import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartMapper {
    public static CartDTO toDto(Optional<Cart> cart) {

        CartDTO dto = new CartDTO();
        dto.setCartId(cart.getId());

        List<CartItemDTO> itemDtos = new ArrayList<>();
        double total = 0;
        for (CartItem item : cart.getCartItems()) {

            CartItemDTO itemDto = new CartItemDTO();
            itemDto.setProductId(item.getProduct().getId());
            itemDto.setProduct(item.getProduct());
            itemDto.setQuantity(item.getQuantity());
            itemDtos.add(itemDto);
        }
        dto.setItems(itemDtos);
        return dto;
    }
}

