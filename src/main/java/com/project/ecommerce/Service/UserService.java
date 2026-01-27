package com.project.ecommerce.Service;

import com.project.ecommerce.Entity.User;
import com.project.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void registerUser(User user){
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
            if(password.equals(user.getPassword())){
                return user;
            }
            else{
                throw new RuntimeException("Wrong Password");
            }
        }
        throw new RuntimeException("Did not Find User with Email : "+email);
    }
}
