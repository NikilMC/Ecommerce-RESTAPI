package com.project.ecommerce.Repository;

import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(Optional<User> user);
}