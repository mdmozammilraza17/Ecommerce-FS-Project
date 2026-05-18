package com.categories.mapper;

import com.categories.dto.CategoryRequestDTO;
import com.categories.dto.CategoryResponseDTO;
import com.categories.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface CategoryMapper {

    // RequestDTO → Entity
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    Category toEntity(CategoryRequestDTO dto);

    // Entity → ResponseDTO
    CategoryResponseDTO toResponseDTO(Category category);
}
