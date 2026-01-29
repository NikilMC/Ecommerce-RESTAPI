package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.CartDTO;
import com.project.ecommerce.DTO.CartMapper;
import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Entity.CartItem;
import com.project.ecommerce.Service.CartService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
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
        Cart cart = cartService.getCartForUser(email);
        CartDTO cartDTO = CartMapper.toDto(cart);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/add/{productId}")
    public void addProduct(@PathVariable int productId, Authentication authentication){
        String email = authentication.name();
        cartService.addProductToCart(email,productId);
    }
}
