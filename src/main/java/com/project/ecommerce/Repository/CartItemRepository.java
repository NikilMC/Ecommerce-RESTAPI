package com.project.ecommerce.Repository;

import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.CartItem;
import com.project.ecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    Optional<CartItem> findByCartAndProduct(Optional<Cart> cart, Product product);
}
