package com.project.ecommerce.DTO;

public class AddToCartRequest {

    private int productId;
    private int quantity;

    public AddToCartRequest() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
