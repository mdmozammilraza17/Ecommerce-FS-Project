package com.banner.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BannerRequestDTO {

    @NotBlank (message = "Title is required")
    private String title;

    private String redirectUrl;

    @NotNull(message = "Active status is required")
    private Boolean active;

    @NotNull(message = "Display order is required")
    private Integer displayOrder;
}
