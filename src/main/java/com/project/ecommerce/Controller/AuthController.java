package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.AuthResponse;
import com.project.ecommerce.DTO.LoginRequest;
import com.project.ecommerce.Entity.User;
import com.project.ecommerce.Service.UserService;
import com.project.ecommerce.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    private JwtService jwtService;

    @Autowired
    public AuthController(UserService userService, JwtService JWT){
        this.userService = userService;
        this.jwtService=JWT;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        User user = userService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}