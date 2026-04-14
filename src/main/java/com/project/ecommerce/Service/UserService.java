package com.project.ecommerce.Service;

import com.project.ecommerce.Entity.User;
import com.project.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user){
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }
        String normalizedRole = user.getRole().replace("ROLE_", "").toUpperCase();
        user.setRole(normalizedRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByEmail(String email){
        Optional<User> result = userRepository.findByEmail(email);
        User user = null;
        if(result.isPresent()){
            user=result.get();
        }
        else{
            throw new RuntimeException("Did not Find User with Email : "+email);
        }
        return user;
    }

    public User authenticate(String email, String password){
        Optional<User> result = userRepository.findByEmail(email);
        User user = null;
        if(result.isPresent()){
            user=result.get();
            if(passwordEncoder.matches(password, user.getPassword())){
                return user;
            }
            else{
                throw new RuntimeException("Wrong Password");
            }
        }
        throw new RuntimeException("Did not Find User with Email : "+email);
    }
}
