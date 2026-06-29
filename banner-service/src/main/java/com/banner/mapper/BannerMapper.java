package com.banner.mapper;

import com.banner.DTO.BannerResponseDTO;
import com.banner.Entity.BannerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    BannerResponseDTO toResponseDto (BannerEntity entity);
}
