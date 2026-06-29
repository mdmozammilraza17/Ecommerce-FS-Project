package com.banner.service;

import com.banner.DTO.BannerRequestDTO;
import com.banner.DTO.BannerResponseDTO;
import com.banner.Entity.BannerEntity;
import org.apache.coyote.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BannerService {
    BannerResponseDTO createBanner(MultipartFile image,
                                          BannerRequestDTO bannerRequestDTO) throws BadRequestException;
}
