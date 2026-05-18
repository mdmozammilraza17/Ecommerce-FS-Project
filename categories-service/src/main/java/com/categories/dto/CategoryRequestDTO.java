package com.categories.dto;

import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String name;
    private Long parentId;

    public CategoryRequestDTO() {
    }

    public CategoryRequestDTO(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}