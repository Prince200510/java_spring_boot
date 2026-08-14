package com.example.demo.controller;
import java.util.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {
    private List<User> users = new ArrayList<>();
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        // List<User> u = List.of(
        //     new User(1,"Prince Maurya", 21),
        //     new User(2,"Rahul Gupta", 22),
        //     new User(3,"Rohit Kumar", 23)
        // );

        // model.addAttribute("users", u);
        // return u;

        // List<User> users = new ArrayList<>();
        return users.stream()
                .filter(user-> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/add")
    public User create(@RequestBody User user) {
        users.add(user);
        return user;
    }
    
}
