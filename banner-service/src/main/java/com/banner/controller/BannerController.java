package com.banner.controller;

import com.banner.DTO.BannerRequestDTO;
import com.banner.DTO.BannerResponseDTO;
import com.banner.config.CloudinaryConfig;
import com.banner.service.BannerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banner")
public class BannerController {

    private final BannerService bannerService;

    // Create Banner
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/create")
    public ResponseEntity<BannerResponseDTO> createBanner (@RequestParam ("image")MultipartFile image,
                                                           @Valid @ModelAttribute BannerRequestDTO bannerRequestDTO) throws BadRequestException {
        BannerResponseDTO response = bannerService.createBanner(image, bannerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All Banner
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping ("/banners")
    public ResponseEntity<List<BannerResponseDTO>> getAllBanner ()
    {
        List<BannerResponseDTO> bannerResponseDTOS = bannerService.fetchAllBanner();
        return ResponseEntity.ok(bannerResponseDTOS);
    }

    @GetMapping
    public String banner ()
    {
        return  "This is banner";
    }
}
