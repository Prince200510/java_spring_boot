package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
   
}

// Spring provides crud now no need to write
// save()
// findAll()
// findById()
// deleteById()
// existsById()