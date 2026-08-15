package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/users_db")
public class UserController_db {
    private final UserRepository userRepository;

    public UserController_db(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("add")
    public User create(@RequestBody User user) {        
        return userRepository.save(user);
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
            .map(user -> {
                user.setName(updatedUser.getName());
                user.setAge(updatedUser.getAge());
                User savedUser = userRepository.save(user);
                return ResponseEntity.ok().body(savedUser);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        if(!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("User with id " + id + " deleted successfully.");
    }
}
