package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.entities.Category;
import com.example.demo.model.entities.Coffee;
import com.example.demo.model.response.PageResponse;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CoffeeRepository;
import com.example.demo.util.PageMapper;

@Service
public class CoffeeService {

        private static final Integer PAGESIZE = 12;
        private final CoffeeRepository coffeeRepository;
        private final CategoryRepository categoryRepository;
        private final PageMapper<Coffee> pageMapper;

        public CoffeeService(CoffeeRepository coffeeRepository, CategoryRepository categoryRepository,
                        PageMapper<Coffee> pageMapper) {
                this.coffeeRepository = coffeeRepository;
                this.categoryRepository = categoryRepository;
                this.pageMapper = pageMapper;
        }

        public PageResponse<Coffee> getAllCoffees(Integer page) {
                Page<Coffee> items = coffeeRepository
                                .findAll(PageRequest.of(page, PAGESIZE));
                return pageMapper
                                .toPageResponse(items);
        }

        public PageResponse<Coffee> getAllCoffesByCategory(Integer categoryId, Integer page) {
                Category category = categoryRepository
                                .findById(categoryId)
                                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
                Page<Coffee> items = coffeeRepository.findCoffeeByCategories(category, PageRequest.of(page, PAGESIZE));
                return pageMapper
                                .toPageResponse(items);
        }

        public PageResponse<Coffee> getCoffeesByName(String name, Integer page) {
                Page<Coffee> items = coffeeRepository.findCoffeeByNameStartingWithIgnoreCase(name,
                                PageRequest.of(page, PAGESIZE));
                return pageMapper.toPageResponse(items);
        }

}
