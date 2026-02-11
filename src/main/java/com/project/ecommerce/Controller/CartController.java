package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.CartDTO;
import com.project.ecommerce.DTO.CartMapper;
import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.CartItem;
import com.project.ecommerce.Service.CartService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService=cartService;
    }

    @GetMapping("")
    public ResponseEntity<CartDTO> getMyCart(Authentication authentication){
        String email = authentication.name();
        CartDTO cartDTO = cartService.getCartForUser(email);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<Void> addProduct(@PathVariable int productId, Authentication authentication){
        String email = authentication.name();
        cartService.addProductToCart(email,productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId, Authentication authentication){
        String email = authentication.name();
        cartService.removeProductFromCart(email, productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Void> updateQuantity(@PathVariable int productId, @RequestParam int qty, Authentication authentication){
        String email = authentication.name();
        cartService.updateQuantity(email, productId, qty);
        return ResponseEntity.ok().build();
    }
}
