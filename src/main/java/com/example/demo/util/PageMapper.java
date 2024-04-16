package com.example.demo.util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.demo.model.response.PageResponse;

@Component
public class PageMapper<T> {
    public PageResponse<T> toPageResponse(Page<T> page) {
        PageResponse<T> response = new PageResponse<>(page
                .getPageable()
                .getPageNumber(),
                page.getTotalPages(),
                page.getContent(),
                page.getSize(),
                page.getNumberOfElements());
        return response;
    }
}
