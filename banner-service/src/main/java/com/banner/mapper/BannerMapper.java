package com.banner.mapper;

import com.banner.DTO.BannerResponseDTO;
import com.banner.Entity.BannerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    BannerResponseDTO toResponseDto (BannerEntity entity);

    List<BannerResponseDTO> toResponseDto (List<BannerEntity> entity);

}
