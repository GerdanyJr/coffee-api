package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

        public PageResponse<Coffee> getAllCoffees(
                        Integer page,
                        String direction,
                        String sort,
                        Integer min,
                        Integer max) {
                Page<Coffee> items = coffeeRepository.findByPriceBetween(
                                BigDecimal.valueOf(min),
                                BigDecimal.valueOf(max != null ? max : 10000),
                                PageRequest.of(page,
                                                PAGESIZE,
                                                Sort.by(Direction.fromString(direction), sort)));
                return pageMapper.toPageResponse(items);
        }

        public PageResponse<Coffee> getAllCoffesByCategories(
                        Integer page,
                        String direction,
                        String sort,
                        Integer min,
                        Integer max,
                        String tagIds) {
                List<Category> foundCategories = categoryRepository.findByIdIn(tagIds.split(","));
                Page<Coffee> items = coffeeRepository.findByCategoriesInAndPriceBetween(
                                foundCategories,
                                BigDecimal.valueOf(min),
                                BigDecimal.valueOf(max != null ? max : 10000),
                                PageRequest.of(page,
                                                PAGESIZE,
                                                Sort.by(Direction.fromString(direction), sort)));
                return pageMapper.toPageResponse(items);
        }

        public PageResponse<Coffee> getCoffeesByName(String name, Integer page) {
                Page<Coffee> items = coffeeRepository.findByNameStartingWithIgnoreCase(name,
                                PageRequest.of(page, PAGESIZE));
                return pageMapper.toPageResponse(items);
        }

        public Coffee getCoffeeById(Integer id) {
                return coffeeRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Café não encontrado com id " + id));
        }

}
