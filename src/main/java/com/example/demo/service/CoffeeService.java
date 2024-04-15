package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Coffee;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CoffeeRepository;

@Service
public class CoffeeService {

    private static final Integer PAGESIZE = 12;
    private final CoffeeRepository coffeeRepository;
    private final CategoryRepository categoryRepository;

    public CoffeeService(CoffeeRepository coffeeRepository, CategoryRepository categoryRepository) {
        this.coffeeRepository = coffeeRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Coffee> getAllCoffees(int page) {
        return coffeeRepository.findAll(PageRequest.of(page, 12));
    }
}
