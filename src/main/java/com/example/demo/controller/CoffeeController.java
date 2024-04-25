package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entities.Category;
import com.example.demo.model.entities.Coffee;
import com.example.demo.model.response.PageResponse;
import com.example.demo.service.CoffeeService;

@RestController
@RequestMapping("/coffee")
@CrossOrigin("${application.crossOriginUrl}")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<Coffee>> getAllCoffees(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(name = "sort", required = false, defaultValue = "price") String sort,
            @RequestParam(name = "min", required = false, defaultValue = "0") Integer min,
            @RequestParam(name = "max", required = false) Integer max,
            @RequestBody(required = false) List<Category> categories) {
        return ResponseEntity.ok(coffeeService.getAllCoffees(page, direction, sort, min, max, categories));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<PageResponse<Coffee>> getCoffeeByCategory(@PathVariable("categoryId") Integer categoryId,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(coffeeService.getAllCoffesByCategory(categoryId, page));
    }

    @GetMapping("/name")
    public ResponseEntity<PageResponse<Coffee>> getCoffeeByName(
            @RequestParam("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(coffeeService.getCoffeesByName(name, page));
    }
}
