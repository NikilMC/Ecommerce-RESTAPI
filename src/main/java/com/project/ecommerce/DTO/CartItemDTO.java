package com.project.ecommerce.DTO;

import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.Product;

public class CartItemDTO {
    private int productId;

    private Product product;

    private int quantity;

    private Cart cart;

    public CartItemDTO(){
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartItemDTO{" +
                "productId=" + productId +
                ", product=" + product +
                ", quantity=" + quantity +
                ", cart=" + cart +
                '}';
    }
}
