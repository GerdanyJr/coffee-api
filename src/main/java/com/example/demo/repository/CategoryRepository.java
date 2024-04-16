package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
