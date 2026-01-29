package com.project.ecommerce.DTO;

import com.project.ecommerce.Entity.User;

import java.util.List;

public class CartDTO {

    private int cartId;

    private List<CartItemDTO> items;

    private User user;

    public CartDTO(){
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartId=" + cartId +
                ", items=" + items +
                ", user=" + user +
                '}';
    }
}
