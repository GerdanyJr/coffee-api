package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entities.Category;
import com.example.demo.model.entities.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
    Page<Coffee> findCoffeeByCategories(Category category, PageRequest pageRequest);

    Page<Coffee> findCoffeeByNameStartingWithIgnoreCase(String name, PageRequest pageRequest);
}
