package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entities.Coffee;
import com.example.demo.model.response.PageResponse;
import com.example.demo.service.CoffeeService;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<Coffee>> getAllCoffees(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(coffeeService.getAllCoffees(page));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<PageResponse<Coffee>> getCoffeeByCategory(@PathVariable("categoryId") Integer categoryId,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(coffeeService.getAllCoffesByCategory(categoryId, page));
    }
}
