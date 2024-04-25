package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entities.Category;
import com.example.demo.model.entities.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
    Page<Coffee> findByCategories(Category category, PageRequest pageRequest);

    Page<Coffee> findByCategoriesInAndPriceBetween(
            List<Category> categories,
            BigDecimal min,
            BigDecimal max,
            PageRequest pageRequest);

    Page<Coffee> findByPriceBetween(
            BigDecimal min,
            BigDecimal max,
            PageRequest pageRequest);

    Page<Coffee> findByNameStartingWithIgnoreCase(String name, PageRequest pageRequest);
}
