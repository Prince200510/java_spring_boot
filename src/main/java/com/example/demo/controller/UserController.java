package com.example.demo.controller;
import java.util.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User obj_update) {
        // for(User user : users) {
        //     if(user.getId() == id) {
        //         user.setName(obj_update.getName());
        //         user.setAge(obj_update.getAge());
        //     }
        // }
        // return getUser(id);
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .map(user -> {
                    user.setName(obj_update.getName());
                    user.setAge(obj_update.getAge());
                    return user;
                })
                .orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        boolean removed = users.removeIf(user -> user.getId() == id);

        if(removed) {
            return "User with ID " + id + " deleted successfully.";
        } else {
            return "User with ID " + id + " not found.";
        }
    }

    @GetMapping()
    public List<User> getAllUsersPrince() {
        return users;
    }
    
}
