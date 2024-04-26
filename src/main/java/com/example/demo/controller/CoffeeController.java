package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
            @RequestParam(name = "max", required = false) Integer max) {
        return ResponseEntity.ok(coffeeService.getAllCoffees(page, direction, sort, min, max));
    }

    @GetMapping("/category")
    public ResponseEntity<PageResponse<Coffee>> getCoffeeByCategory(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(name = "sort", required = false, defaultValue = "price") String sort,
            @RequestParam(name = "min", required = false, defaultValue = "0") Integer min,
            @RequestParam(name = "max", required = false) Integer max,
            @RequestParam(name = "tags", required = false) String tagIds) {
        return ResponseEntity.ok(coffeeService.getAllCoffesByCategories(page, direction, sort, min, max, tagIds));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(coffeeService.getCoffeeById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<PageResponse<Coffee>> getCoffeeByName(
            @RequestParam("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(coffeeService.getCoffeesByName(name, page));
    }
}
