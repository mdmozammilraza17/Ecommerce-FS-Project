package com.banner.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerResponseDTO {

    private Long id;

    private String title;

    private String imageUrl;

    private String redirectUrl;

    private Boolean active;

    private Integer displayOrder;

    private LocalDateTime createdAt;
}
