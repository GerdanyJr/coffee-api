package com.example.demo.model.response;

import java.util.List;

public record PageResponse<T>(
        Integer pageNumber,
        Integer totalPages,
        List<T> results,
        Integer pageSize,
        Integer numberOfElements) {
}
