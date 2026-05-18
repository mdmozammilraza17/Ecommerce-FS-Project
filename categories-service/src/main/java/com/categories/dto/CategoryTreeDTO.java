package com.categories.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryTreeDTO {

    private Long id;
    private String name;
    private List<CategoryTreeDTO> children;

    public CategoryTreeDTO() {
    }

    public CategoryTreeDTO(Long id, String name, List<CategoryTreeDTO> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }
}