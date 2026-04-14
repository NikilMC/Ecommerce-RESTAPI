package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.CartDTO;
import com.project.ecommerce.Service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String email = authentication.getName();
        CartDTO cartDTO = cartService.getCartForUser(email);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<Void> addProduct(@PathVariable int productId, Authentication authentication){
        String email = authentication.getName();
        cartService.addProductToCart(email,productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId, Authentication authentication){
        String email = authentication.getName();
        cartService.removeProductFromCart(email, productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Void> updateQuantity(@PathVariable int productId, @RequestParam int qty, Authentication authentication){
        String email = authentication.getName();
        cartService.updateQuantity(email, productId, qty);
        return ResponseEntity.ok().build();
    }
}
