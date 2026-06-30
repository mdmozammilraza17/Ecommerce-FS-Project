package com.banner.service;

import com.banner.DTO.BannerRequestDTO;
import com.banner.DTO.BannerResponseDTO;
import com.banner.DTO.CloudinaryResponse;
import com.banner.Entity.BannerEntity;
import com.banner.exception.BadRequestException;
import com.banner.mapper.BannerMapper;
import com.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService{

    private final BannerRepository bannerRepository;
    private final CloudinaryService cloudinaryService;
    private final BannerMapper bannerMapper;

    @Override
    public BannerResponseDTO createBanner(MultipartFile image,
                                          BannerRequestDTO bannerRequestDTO) throws BadRequestException {

        if (image == null || image.isEmpty())
        {
            throw new BadRequestException("Banner image is required");
        }

        if (bannerRepository.existsByDisplayOrder(bannerRequestDTO.getDisplayOrder()))
        {
            throw new BadRequestException("Display order " + bannerRequestDTO.getDisplayOrder()+ " already exists.");
        }

        CloudinaryResponse cloudinaryResponse = cloudinaryService.uploadImage(image);

        BannerEntity bannerEntity = new BannerEntity();
        bannerEntity.setTitle(bannerRequestDTO.getTitle());
        bannerEntity.setImageUrl(cloudinaryResponse.getImageUrl());
        bannerEntity.setPublicId(cloudinaryResponse.getPublicId());
        bannerEntity.setRedirectUrl(bannerRequestDTO.getRedirectUrl());
        bannerEntity.setDisplayOrder(bannerRequestDTO.getDisplayOrder());
        bannerEntity.setActive(bannerRequestDTO.getActive());

        BannerEntity savedBanner = bannerRepository.save(bannerEntity);

        return bannerMapper.toResponseDto(savedBanner);

    }

    @Override
    public List<BannerResponseDTO> fetchAllBanner() {
        List<BannerEntity> getAllBanner = bannerRepository.findAll();
        return getAllBanner.stream()
                .map(bannerMapper::toResponseDto).toList();
    }

}
