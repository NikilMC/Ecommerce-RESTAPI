package com.project.ecommerce.Controller;


import com.project.ecommerce.DTO.CartDTO;
import com.project.ecommerce.DTO.CartMapper;
import com.project.ecommerce.Entity.Cart;
import com.project.ecommerce.Service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private CartService cartService;

    @GetMapping
    public ResponseEntity<CartDTO> getCart(Authentication auth){
        return ResponseEntity.ok(
                cartService.getCartForUser(auth.getName())
        );
    }

    @PostMapping("/products")
    public ResponseEntity<CartDTO> addProduct(Authentication auth, @RequestBody AddToCartRequest request){

        return ResponseEntity.ok(
                cartService.addProductToCart(auth.getName(), request.getProductId(), request.getQuantity()
                )
        );
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<CartDTO> updateQuantity(Authentication auth, @PathVariable int productId, @RequestParam int quantity){

        return ResponseEntity.ok(
                cartService.updateQuantity(auth.getName(), productId, quantity)
        );
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<CartDTO> removeProduct(Authentication auth, @PathVariable int productId){
        return ResponseEntity.ok(
                cartService.removeProductFromCart(auth.getName(), productId)
        );
    }
}
