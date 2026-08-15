package com.example.demo.controller;

import com.example.demo.service.FactorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factorial")
public class FactorialController {
    private final FactorialService factorialService;

    @Autowired
    public FactorialController(FactorialService factorialService) {
        this.factorialService = factorialService;
    }

    @GetMapping("/{number}")
    public long getFactorial(@PathVariable int number) {
        return factorialService.calculateFactorial(number);
    }
}
